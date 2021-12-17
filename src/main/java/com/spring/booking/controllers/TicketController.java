package com.spring.booking.controllers;

import com.spring.booking.dao.OrderDAO;
import com.spring.booking.dao.TicketDAO;
import com.spring.booking.models.Order;
import com.spring.booking.models.Route;
import com.spring.booking.models.Ticket;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class TicketController {

    private final TicketDAO ticketDAO;
    private final OrderDAO orderDAO;

    public TicketController(TicketDAO ticketDAO, OrderDAO orderDAO) {
        this.ticketDAO = ticketDAO;
        this.orderDAO = orderDAO;
    }

    @GetMapping
    public List<Route> getRoutes(Model model) {
        return ticketDAO.showRoutes();
    }

    @GetMapping("/{id}")
    public List<Ticket> getTickets(@PathVariable("id") int id) {
        return ticketDAO.getTickets(id);
    }

    @PostMapping()
    public void makeOrder(@RequestBody Order order) {
        orderDAO.addOrder(order);
    }

    @GetMapping("/profile")
    public List<Order> getOrders() {
        return orderDAO.loadAccountOrders();
    }

    @DeleteMapping("/profile/{id}")
    public void delete(@PathVariable("id") int id) {
        orderDAO.deleteOrder(id);
    }
}
