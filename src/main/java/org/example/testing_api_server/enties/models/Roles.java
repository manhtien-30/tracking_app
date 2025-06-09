package org.example.testing_api_server.enties.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.testing_api_server.enties.models.Enum.ERole;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Roles {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;
    @Column(name = "role_name")
    @Enumerated(EnumType.STRING)
    private ERole name;
    public Roles(ERole name) {
        this.name = name;
    }
}
