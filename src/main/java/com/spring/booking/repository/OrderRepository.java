package com.spring.booking.repository;

import com.spring.booking.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    void deleteByIdAndAccountId(int id, int accountId);

    List<Order> findAllByAccountId(int id);

    @Query(value = "SELECT * FROM orders WHERE MONTH(date) = MONTH(?1)",
    nativeQuery = true)
    List<Order> findAllByMonth(Date date);
    int countByAccountId(int id);
}
