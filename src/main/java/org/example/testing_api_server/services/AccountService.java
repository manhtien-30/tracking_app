package org.example.testing_api_server.services;

import org.example.testing_api_server.enties.dto.AccountDto;
import org.example.testing_api_server.enties.dto.SignUpRequestDto;
import org.example.testing_api_server.enties.models.Account;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    void save(Account account);
    void updateLastlogin(String username, OffsetDateTime lastLogin);
    void update(Account account);
    Boolean existByEmail(String email);
    Boolean existByUsername(String username);

    void updateAccountProfile(Account account);
    Optional<Account> findByUsername(String username);


    List<AccountDto> getListAccounts();


}