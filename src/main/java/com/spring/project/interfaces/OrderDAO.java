package com.spring.project.interfaces;

import com.spring.project.models.Order;

import java.util.List;

public interface OrderDAO {

    void addOrder(Order order);
    void deleteOrder(int id);
    List<Order> loadAccountOrders();
}
