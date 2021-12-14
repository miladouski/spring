package com.spring.booking.dao;

import com.spring.booking.models.Route;
import com.spring.booking.models.Ticket;

import java.util.List;

public interface TicketDAO {
    List<Route> showRoutes();
    List<Ticket> getTickets(int id);
}
