package com.spring.booking.service;

import com.spring.booking.models.Account;
import com.spring.booking.models.AuthenticationRequest;
import com.spring.booking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spring.booking.constants.ConstantsSQL.*;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }

    public boolean saveAccount(AuthenticationRequest request) {
        Account accountFromDB = loadUserByUsername(request.getUsername());
        if(accountFromDB != null) {
            return false;
        }
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        accountRepository.save(new Account(request.getUsername(), request.getPassword()));
        return true;
    }
}
