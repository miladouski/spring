package com.spring.project.dao;

import com.spring.project.interfaces.AccountDAO;
import com.spring.project.interfaces.OrderDAO;
import com.spring.project.models.Order;
import com.spring.project.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.spring.project.constants.ConstantsSQL.*;

@Component
public class OrderDAOImpl implements OrderDAO {

    private final JdbcTemplate jdbcTemplate;
    private final AccountDAO accountDAO;

    @Autowired
    public OrderDAOImpl(JdbcTemplate jdbcTemplate, AccountDAO accountDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountDAO = accountDAO;
    }

    private int getCurrentAccountId() {
        return accountDAO.getAccount(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
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
