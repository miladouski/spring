package com.spring.booking.dao.impl;

import com.spring.booking.dao.OrderDAO;
import com.spring.booking.models.Order;
import com.spring.booking.models.Ticket;
import com.spring.booking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.spring.booking.constants.ConstantsSQL.*;

@Component
public class OrderDAOImpl implements OrderDAO {

    private final JdbcTemplate jdbcTemplate;
    private final AccountService accountService;

    @Autowired
    public OrderDAOImpl(JdbcTemplate jdbcTemplate, AccountService accountService) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountService = accountService;
    }

    private int getCurrentAccountId() {
        return accountService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
    }

    @Override
    public void addOrder(Order order) {
        jdbcTemplate.update(ADD_ORDER_QUERY, getCurrentAccountId(), order.getTicket().getId(), order.getAmount());
    }

    @Override
    public void deleteOrder(int id) {
        jdbcTemplate.update(DELETE_ORDER_QUERY, id, getCurrentAccountId());
    }

    @Override
    public List<Order> loadAccountOrders() {
        return jdbcTemplate.query(ACCOUNT_ORDERS_QUERY,
                new Object[]{getCurrentAccountId()}, (rs, rowNum) -> {
                    Order order = new Order();
                    order.setId(rs.getInt(ORDERS_ID_COLUMN));
                    order.setTicket(new Ticket(rs.getInt(TICKETS_ID_COLUMN),
                            rs.getString(ROUTE_COLUMN),
                            rs.getDate(DATE_COLUMN)));
                    order.setAmount(rs.getInt(AMOUNT_COLUMN));
                    return order;
                });
    }

}
