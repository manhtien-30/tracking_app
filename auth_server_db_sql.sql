

-- Enable critical extensions
CREATE EXTENSION IF NOT EXISTS pgcrypto;  -- For cryptographic functions
CREATE EXTENSION IF NOT EXISTS citext;   -- For case-insensitive email
-- Core account table
CREATE TABLE accounts (
    account_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE CHECK (username ~ '^[a-zA-Z0-9_]{3,50}$'),
    email CITEXT NOT NULL UNIQUE CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'),
    password_hash TEXT NOT NULL CHECK (length(password_hash) = 97),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_login TIMESTAMPTZ,
    is_verified BOOLEAN NOT NULL DEFAULT false,
    is_active BOOLEAN NOT NULL DEFAULT true,
    is_locked BOOLEAN NOT NULL DEFAULT false,
    failed_attempts SMALLINT NOT NULL DEFAULT 0 CHECK (failed_attempts >= 0),
    mfa_enabled BOOLEAN NOT NULL DEFAULT false
);

-- Account profile details
CREATE TABLE account_profiles (
    account_id INT PRIMARY KEY REFERENCES accounts(account_id) ON DELETE CASCADE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20) CHECK (phone ~ '^\+?[0-9]{1,15}$'),
    locale VARCHAR(10) NOT NULL DEFAULT 'en-US',
    timezone VARCHAR(50) NOT NULL DEFAULT 'UTC',
    avatar_url TEXT
);

-- Password history for rotation enforcement
CREATE TABLE password_history (
    password_id SERIAL PRIMARY KEY,
    account_id INT NOT NULL REFERENCES accounts(account_id) ON DELETE CASCADE,
    password_hash TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Roles and permissions
CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(30) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE permissions (
    permission_id SERIAL PRIMARY KEY,
    permission_name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

-- Account role assignments
CREATE TABLE account_roles (
    account_id INT NOT NULL REFERENCES accounts(account_id) ON DELETE CASCADE,
    role_id INT NOT NULL REFERENCES roles(role_id) ON DELETE CASCADE,
    PRIMARY KEY (account_id, role_id)
);

-- Role permission assignments
CREATE TABLE role_permissions (
    role_id INT NOT NULL REFERENCES roles(role_id) ON DELETE CASCADE,
    permission_id INT NOT NULL REFERENCES permissions(permission_id) ON DELETE CASCADE,
    PRIMARY KEY (role_id, permission_id)
);

-- Session management with JWT support
CREATE TABLE sessions (
    session_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id INT NOT NULL REFERENCES accounts(account_id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    expires_at TIMESTAMPTZ NOT NULL,
    ip_address INET NOT NULL,
    user_agent TEXT,
    refresh_token TEXT NOT NULL UNIQUE,
    device_fingerprint TEXT,
    is_revoked BOOLEAN NOT NULL DEFAULT false
);

-- Authentication tokens (email verification, password reset)
CREATE TYPE token_type AS ENUM (
    'email_verification',
    'password_reset',
    'mfa_recovery',
    'api_access'
);

CREATE TABLE auth_tokens (
    token_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id INT NOT NULL REFERENCES accounts(account_id) ON DELETE CASCADE,
    token_type token_type NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    expires_at TIMESTAMPTZ NOT NULL,
    used_at TIMESTAMPTZ,
    token_hash TEXT NOT NULL
);

-- Login audit log
CREATE TABLE login_attempts (
    attempt_id BIGSERIAL PRIMARY KEY,
    account_id INT REFERENCES accounts(account_id) ON DELETE SET NULL,
    attempted_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    successful BOOLEAN NOT NULL,
    ip_address INET NOT NULL,
    user_agent TEXT,
    failure_reason TEXT
);

-- Multi-factor authentication
CREATE TYPE mfa_method AS ENUM ('totp', 'webauthn', 'sms', 'email');

CREATE TABLE mfa_devices (
    device_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id INT NOT NULL REFERENCES accounts(account_id) ON DELETE CASCADE,
    method mfa_method NOT NULL,
    name VARCHAR(50) NOT NULL,
    secret TEXT,  -- Encrypted with pgp_sym_encrypt
    public_key TEXT,  -- For WebAuthn
    phone VARCHAR(20), -- For SMS
    last_used_at TIMESTAMPTZ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- Security keys for WebAuthn
CREATE TABLE security_keys (
    key_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_id INT NOT NULL REFERENCES accounts(account_id) ON DELETE CASCADE,
    credential_id TEXT NOT NULL UNIQUE,
    public_key TEXT NOT NULL,
    sign_count INT NOT NULL DEFAULT 0,
    transports VARCHAR(50)[],
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    last_used_at TIMESTAMPTZ
);

-- Audit log for security events
CREATE TABLE security_events (
    event_id BIGSERIAL PRIMARY KEY,
    account_id INT REFERENCES accounts(account_id) ON DELETE SET NULL,
    event_type VARCHAR(50) NOT NULL,
    event_time TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    ip_address INET,
    user_agent TEXT,
    details JSONB
);

-- Indexes for performance
CREATE INDEX idx_accounts_email ON accounts(email);
CREATE INDEX idx_sessions_account ON sessions(account_id);
CREATE INDEX idx_sessions_expires ON sessions(expires_at);
CREATE INDEX idx_tokens_expires ON auth_tokens(expires_at);
CREATE INDEX idx_login_attempts_account ON login_attempts(account_id, attempted_at);
CREATE INDEX idx_security_events_account ON security_events(account_id, event_time);

-- Trigger for password history
CREATE OR REPLACE FUNCTION log_password_history()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.password_hash <> OLD.password_hash THEN
        INSERT INTO password_history (account_id, password_hash)
        VALUES (NEW.account_id, NEW.password_hash);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_password_change
AFTER UPDATE OF password_hash ON accounts
FOR EACH ROW
EXECUTE FUNCTION log_password_history();

-- Trigger for security events logging
CREATE OR REPLACE FUNCTION log_security_event()
RETURNS TRIGGER AS $$
DECLARE
    event_details JSONB;
BEGIN
    event_details := jsonb_build_object(
        'table', TG_TABLE_NAME,
        'action', TG_OP,
        'old', row_to_json(OLD),
        'new', row_to_json(NEW)
    );
    
    INSERT INTO security_events (account_id, event_type, details)
    VALUES (NEW.account_id, TG_OP || '_' || TG_TABLE_NAME, event_details);
    
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_account_security
AFTER UPDATE ON accounts
FOR EACH ROW
EXECUTE FUNCTION log_security_event();

-- Sample data
INSERT INTO roles (role_name, description) VALUES
('user', 'Regular authenticated user'),
('admin', 'System administrator with full privileges'),
('moderator', 'Content moderator');

INSERT INTO permissions (permission_name, description) VALUES
('account:read', 'View account information'),
('account:write', 'Modify account information'),
('account:delete', 'Delete user accounts'),
('session:manage', 'Manage active sessions'),
('role:assign', 'Assign roles to users'),
('system:config', 'Change system configuration');

-- Admin user (password: AdminPassword123! - use argon2id in app)
INSERT INTO accounts (username, email, password_hash) VALUES
('admin', 'admin@example.com', '$argon2id$v=19$m=65536,t=3,p=4$dGVzdHNhbHQ$5kzFKgN5cBMQqQ7D9QzW1g6gL0DmZ7sRzq2+U0X3X0I');

INSERT INTO account_profiles (account_id, first_name, last_name) VALUES
(1, 'Admin', 'User');

INSERT INTO account_roles (account_id, role_id) VALUES
(1, (SELECT role_id FROM roles WHERE role_name = 'admin'));

INSERT INTO role_permissions (role_id, permission_id)
SELECT r.role_id, p.permission_id
FROM roles r
CROSS JOIN permissions p
WHERE r.role_name = 'admin';
