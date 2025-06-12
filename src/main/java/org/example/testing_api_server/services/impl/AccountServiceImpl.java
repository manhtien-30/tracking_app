package org.example.testing_api_server.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.testing_api_server.enties.dto.SignUpRequestDto;
import org.example.testing_api_server.enties.models.Account;
import org.example.testing_api_server.repositories.AccountProfilesRepository;
import org.example.testing_api_server.repositories.AccountRepository;
import org.example.testing_api_server.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private AccountProfilesRepository accountProfilesRepository;
    @Override
    //@Transactional
    public void save(Account account) {
        //accountProfilesRepository.save(account.getAccountProfiles());
        accountRepository.save(account);
    }

    @Override
    public void update(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Boolean existByEmail(String email) { return accountRepository.existsByEmail(email);
    }

    @Override
    public Boolean existByUsername(String username) {
        return accountRepository.existsByUsername(username);
    }

    @Override
    public Optional<Account> findByUsername(String username) {

        return accountRepository.findByUsername(username);
    }
    //method: get list of all account, return list of accountDto
    //TODO: implement method
    @Override
    public List<Account> getListAccounts() {

        return accountRepository.findAll();
    }

}
