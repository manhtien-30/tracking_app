package org.example.testing_api_server.services.impl;

import org.example.testing_api_server.enties.models.Account;
import org.springframework.stereotype.Service;


@Service
public interface AccountServiceImpl {
    void save(Account account);
    Boolean existByEmail(String email);
    Boolean existByUsername(String username);
}
