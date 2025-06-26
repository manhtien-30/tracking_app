package org.example.testing_api_server.enties.dto;

import lombok.*;
import org.example.testing_api_server.enties.models.Roles;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer accountId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Set<Roles> roles;
}
