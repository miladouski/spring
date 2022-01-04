package com.spring.booking.controllers;

import com.spring.booking.models.Order;
import com.spring.booking.models.Route;
import com.spring.booking.models.Ticket;
import com.spring.booking.service.OrderService;
import com.spring.booking.service.TicketService;
import com.spring.booking.specification.SearchOperation;
import com.spring.booking.specification.TicketSpecificationsBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/routes")
public class TicketController {

    private final TicketService ticketService;
    private final OrderService orderService;

    public TicketController(TicketService ticketService, OrderService orderService) {
        this.ticketService = ticketService;
        this.orderService = orderService;
    }

    @GetMapping
    public List<Route> getRoutes() {
        return ticketService.getAllRoutes();
    }

    @GetMapping("/{id}")
    public List<Ticket> getTickets(@PathVariable("id") int id) {
        return ticketService.getTickets(id);
    }

    @PostMapping()
    public void makeOrder(@Validated @RequestBody Order order) {
        orderService.addOrder(order);
    }

    @GetMapping("/criteria")
    public List<Ticket> criteriaSearch(@RequestParam(value = "search") String search) {
        TicketSpecificationsBuilder builder = new TicketSpecificationsBuilder();
        Pattern pattern = Pattern.compile("([\\w.]+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1),
                    SearchOperation.getOperation(matcher.group(2)),
                    matcher.group(3));
        }

        Specification<Ticket> specification = builder.build();
        return ticketService.searchTickets(specification);
    }
}
