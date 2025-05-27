CREATE TABLE roles
(
    id_role BIGINT NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id_role)
);
CREATE TABLE roles
(
    id_role   BIGINT NOT NULL,
    role      VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id_role)
);
Alter table roles
    add column accountid Integer;
ALTER TABLE roles
    ADD CONSTRAINT FK_ROLES_ON_ACCOUNTID FOREIGN KEY (accountid) REFERENCES accounts (accountid);