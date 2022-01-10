package com.spring.booking.service;

import com.spring.booking.exceptions.JwtAuthenticationException;
import com.spring.booking.exceptions.RegistrationException;
import com.spring.booking.exceptions.WrongArgumentException;
import com.spring.booking.models.Account;
import com.spring.booking.models.AccountPhoto;
import com.spring.booking.models.responses.ReportResponse;
import com.spring.booking.repository.AccountRepository;
import com.spring.booking.repository.OrderRepository;
import com.spring.booking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final OrderRepository orderRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountService(AccountRepository accountRepository, RoleRepository roleRepository, OrderRepository orderRepository) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
    }

    public void saveAccount(Account account) {
        Account accountFromDB = accountRepository.findByUsername(account.getUsername());
        if(accountFromDB != null) {
            throw new RegistrationException("Account already exists");
        }
        account.setRegDate(new Date());
        account.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
        account.setStatus(Boolean.TRUE);
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getCurrentAccount() {
        return accountRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public void changeStatus(Account request) {
        Optional<Account> account = accountRepository.findById(request.getId());
        if (!account.isPresent()) {
            throw new WrongArgumentException("Account not found");
        }
        account.get().setStatus(request.isEnabled());
        accountRepository.save(account.get());
    }

    public void changePhoto(byte[] photo) {
       Account account = accountRepository.getById(getCurrentAccount().getId());
       account.setAccountPhoto(new AccountPhoto(getCurrentAccount().getId(), photo, getCurrentAccount()));
       accountRepository.save(account);
    }

    public void editProfile(Account form) {
        Account account = getCurrentAccount();
        if (form.getUsername() != null) {
            if(accountRepository.findByUsername(form.getUsername()) != null) {
                throw new RegistrationException("Username already exists");
            }
            account.setUsername(form.getUsername());
        }
        if (form.getEmail() != null) {
            account.setEmail(form.getEmail());
        }
        if (form.getPassword() != null) {
            account.setPassword(bCryptPasswordEncoder.encode(form.getPassword()));
        }
        accountRepository.save(account);
    }

    public void editRoles(Account form) {
        Optional<Account> optional = accountRepository.findById(form.getId());
        if (!optional.isPresent()) {
            throw new WrongArgumentException("Account not found");
        }
        Account account = optional.get();
        account.setRoles(form.getRoles());
        accountRepository.save(account);
    }

    public List<ReportResponse> getReport(Date date) {
        List<Account> accounts = accountRepository.findAllByRegDate(date);
        return accounts.stream()
                .map(account -> new ReportResponse(account.getUsername(),
                        account.getEmail(), account.getRegDate(), orderRepository.countByAccountId(account.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public Account loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null) {
            throw new JwtAuthenticationException("Account does not exist");
        }
        return account;
    }
}
