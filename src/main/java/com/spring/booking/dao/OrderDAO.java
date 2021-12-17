package com.spring.booking.dao;

import com.spring.booking.models.Order;

import java.util.List;

public interface OrderDAO {

    void addOrder(Order order);
    void deleteOrder(int id);
    List<Order> loadAccountOrders();
}
