package com.spring.booking.dao.impl;

import com.spring.booking.dao.TicketDAO;
import com.spring.booking.models.Route;
import com.spring.booking.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.spring.booking.constants.ConstantsSQL.*;

@Component
public class TicketDAOImpl implements TicketDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Route> showRoutes() {
        return jdbcTemplate.query(GET_ALL_ROUTES_QUERY, new Object[] {}, new BeanPropertyRowMapper<>(Route.class));
    }

    @Override
    public List<Ticket> getTickets(int id) {
        return jdbcTemplate.query(GET_ROUTE_TICKETS, new Object[] {id}, new BeanPropertyRowMapper<>(Ticket.class));
    }
}
