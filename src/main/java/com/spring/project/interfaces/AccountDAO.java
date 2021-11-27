package com.spring.project.interfaces;

import com.spring.project.models.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountDAO extends UserDetailsService {
    Account getAccount(String username);
    boolean saveAccount(Account account);
}
