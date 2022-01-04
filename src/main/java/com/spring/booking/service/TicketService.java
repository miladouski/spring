package com.spring.booking.service;

import com.spring.booking.models.Route;
import com.spring.booking.models.Ticket;
import com.spring.booking.repository.RouteRepository;
import com.spring.booking.repository.TicketRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final RouteRepository routeRepository;

    public TicketService(TicketRepository ticketRepository, RouteRepository routeRepository) {
        this.ticketRepository = ticketRepository;
        this.routeRepository = routeRepository;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public List<Ticket> getTickets(int id) {
        return ticketRepository.findAllByRouteId(id);
    }

    public List<Ticket> searchTickets(Specification<Ticket> specification) {
        return ticketRepository.findAll(specification);
    }
}
