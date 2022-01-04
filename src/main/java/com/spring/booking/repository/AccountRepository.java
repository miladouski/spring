package com.spring.booking.repository;

import com.spring.booking.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByUsername(String username);

    @Query(value = "SELECT * FROM accounts WHERE MONTH(reg_date) = MONTH(?1)",
            nativeQuery = true)
    List<Account> findAllByRegDate(Date date);
}
