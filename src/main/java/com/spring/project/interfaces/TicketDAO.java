package com.spring.project.interfaces;

import com.spring.project.models.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketDAO {
    Map<Integer, String> showRoutes();
    List<Ticket> getTickets(int id);
}
