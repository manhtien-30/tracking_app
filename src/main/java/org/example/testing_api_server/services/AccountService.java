package org.example.testing_api_server.services;

import org.example.testing_api_server.enties.models.Account;
import org.springframework.stereotype.Service;

public interface AccountService {
    void save(Account account);

    Boolean existByEmail(String email);
    Boolean existByUsername(String username);
}