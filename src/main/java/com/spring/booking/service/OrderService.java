package com.spring.booking.service;

import com.spring.booking.models.Account;
import com.spring.booking.models.Order;
import com.spring.booking.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AccountService accountService;

    public OrderService(OrderRepository orderRepository, AccountService accountService) {
        this.orderRepository = orderRepository;
        this.accountService = accountService;
    }

    public void addOrder(Order order) {
        order.setAccountId(accountService.getCurrentAccount().getId());
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(int id) {
        orderRepository.deleteByIdAndAccountId(id, accountService.getCurrentAccount().getId());
    }

    public List<Order> getAccountOrders(Account account) {
        return orderRepository.findAllByAccountId(account.getId());
    }

    public List<Order> getAccountOrders() {
        return getAccountOrders(accountService.getCurrentAccount());
    }

    public List<Order> getMonthOrders(Date date) {
        return orderRepository.findAllByMonth(date);
    }
}
