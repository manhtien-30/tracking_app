package org.example.testing_api_server.enties.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "permissions")
@Data
public class Permissions {
    @Id
    private Integer permissionId;
    @Column(name = "permission_Name")
    private String permissionName;
    @Column(name = "description")
    private String permissionDescription;

}
