package org.example.testing_api_server.enties.dto;

import lombok.*;
import org.example.testing_api_server.enties.models.Roles;

import java.time.OffsetDateTime;
import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private String username;
    private OffsetDateTime lastLogin;
    private List<Roles> roles;
}
