package org.example.testing_api_server.enties.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.security.Timestamp;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "accounts")
public class Account {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User userid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountid", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @ColumnDefault("true")
    @Column(name = "is_active")
    private Boolean isActive;

    @ColumnDefault("false")
    @Column(name = "is_verified")
    private Boolean isVerified;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expires")
    private OffsetDateTime resetTokenExpires;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "roles",
            joinColumns = @JoinColumn(name = "accountid"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Roles> roles = new HashSet<>();
}
