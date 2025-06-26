package org.example.testing_api_server.controller;

import org.example.testing_api_server.enties.dto.AccountDto;
import org.example.testing_api_server.enties.dto.ApiResponseDto;
import org.example.testing_api_server.enties.models.Account;
import org.example.testing_api_server.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/accounts")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AccountDto> getAccounts() {
        return accountService.getListAccounts();
    }
}
