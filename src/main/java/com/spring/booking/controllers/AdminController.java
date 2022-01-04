package com.spring.booking.controllers;

import com.spring.booking.models.Account;
import com.spring.booking.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    AccountService accountService;

    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public List<Account> getAccountsList() {
        return accountService.getAllAccounts();
    }

    @PutMapping("/account/status")
    public void changeAccountStatus(@RequestBody Account account) {
        accountService.changeStatus(account);
    }

    @PutMapping("/account/role")
    public void editAccountRoles(@RequestBody Account account) {
        accountService.editRoles(account);
    }

}
