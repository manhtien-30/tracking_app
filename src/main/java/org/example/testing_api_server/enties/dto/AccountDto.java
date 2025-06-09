package org.example.testing_api_server.enties.dto;

import lombok.*;
import org.example.testing_api_server.enties.models.Roles;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer id;
    private String username;
    private String email;
    private Boolean isActive;
    private List<Roles> roles;
}
