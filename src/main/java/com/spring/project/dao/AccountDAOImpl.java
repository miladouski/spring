package com.spring.project.dao;

import com.spring.project.interfaces.AccountDAO;
import com.spring.project.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import static com.spring.project.constants.ConstantsSQL.*;

@Component
public class AccountDAOImpl implements AccountDAO {

    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountDAOImpl(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = getAccount(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return getAccount(username);
    }

    @Override
    public Account getAccount(String username) {
        return jdbcTemplate.query(GET_ACCOUNT_QUERY, new Object[]{username}, new BeanPropertyRowMapper<>(Account.class))
                .stream().findAny().orElse(null);
    }

    @Override
    public boolean saveAccount(Account account) {
        Account accountFromDB = getAccount(account.getUsername());
        if(accountFromDB != null) {
            return false;
        }
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        jdbcTemplate.update(SAVE_ACCOUNT_QUERY, account.getUsername(), account.getPassword());
        return true;
    }
}
