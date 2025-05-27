package org.example.testing_api_server.enties.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    @ManyToOne
    @JoinColumn(name = "accountid")
    private Account account;
    @Enumerated(EnumType.STRING)
    private ERole role;
    public Roles(ERole name) {
        this.role = name;
    }
}
