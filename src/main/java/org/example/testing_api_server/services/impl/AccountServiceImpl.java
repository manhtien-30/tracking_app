package org.example.testing_api_server.services.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.testing_api_server.enties.dto.AccountDto;
import org.example.testing_api_server.enties.dto.SignUpRequestDto;
import org.example.testing_api_server.enties.models.Account;
import org.example.testing_api_server.repositories.AccountProfilesRepository;
import org.example.testing_api_server.repositories.AccountRepository;
import org.example.testing_api_server.services.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public void updateLastlogin(String username, OffsetDateTime lastLogin) {
        Account account = accountRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
        account.setLastLogin(lastLogin);
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
    public void updateAccountProfile(Account account) {

    }

    @Override
    public Optional<Account> findByUsername(String username) {

        return accountRepository.findByUsername(username);
    }
    @Override
    public List<AccountDto> getListAccounts() {
        return accountRepository.getListAccounts()
                .stream()
                .map(this::toAccountDto)
                .collect(Collectors.toList());
    }

    private AccountDto toAccountDto(Account account) {
        return AccountDto.builder()
                .accountId(account.getId())
                .username(account.getUsername())
                .email(account.getEmail())
                .firstName(account.getAccountProfiles().getFirstName())
                .lastName(account.getAccountProfiles().getLastName())
                .phone(account.getAccountProfiles().getPhone())
                .roles(account.getRoles())
                .build();
    }

}
