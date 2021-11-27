package com.spring.project.dao;

import com.spring.project.interfaces.TicketDAO;
import com.spring.project.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spring.project.constants.ConstantsSQL.*;

@Component
public class TicketDAOImpl implements TicketDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<Integer, String> showRoutes() {
        return jdbcTemplate.query(GET_ALL_ROUTES_QUERY, rs -> {
            Map<Integer,String> routeMap= new HashMap<>();
            while(rs.next()){
                routeMap.put(rs.getInt(ROUTES_ID_COLUMN),rs.getString(ROUTE_COLUMN));
            }
            return routeMap;
        });
    }

    @Override
    public List<Ticket> getTickets(int id) {
        return jdbcTemplate.query(GET_ROUTE_TICKETS, new Object[] {id}, new BeanPropertyRowMapper<>(Ticket.class));
    }
}
