package org.example.testing_api_server.services;

import org.example.testing_api_server.enties.dto.SignUpRequestDto;
import org.example.testing_api_server.enties.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    void save(Account account);


    void update(Account account);
    Boolean existByEmail(String email);
    Boolean existByUsername(String username);

    Optional<Account> findByUsername(String username);

    List<Account> getListAccounts();


}