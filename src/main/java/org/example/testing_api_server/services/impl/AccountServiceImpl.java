package org.example.testing_api_server.services.impl;

import org.example.testing_api_server.enties.models.Account;
import org.example.testing_api_server.repositories.AccountRepository;
import org.example.testing_api_server.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Boolean existByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Boolean existByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }
}
