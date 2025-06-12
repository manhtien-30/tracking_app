package org.example.testing_api_server.services.impl;

import jakarta.transaction.Transactional;
import org.example.testing_api_server.enties.models.Account;
import org.example.testing_api_server.enties.models.UserDetailsImpl;
import org.example.testing_api_server.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account =  accountRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
       // System.out.println(account.toString());
        return UserDetailsImpl.build(account);
    }
}
