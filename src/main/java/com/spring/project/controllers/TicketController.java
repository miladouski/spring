package com.spring.project.controllers;

import com.spring.project.interfaces.OrderDAO;
import com.spring.project.interfaces.TicketDAO;
import com.spring.project.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.spring.project.constants.ConstantsView.*;

@Controller
@RequestMapping("/routes")
public class TicketController {

    private final TicketDAO ticketDAO;
    private final OrderDAO orderDAO;

    @Autowired
    public TicketController(TicketDAO ticketDAO, OrderDAO orderDAO) {
        this.ticketDAO = ticketDAO;
        this.orderDAO = orderDAO;
    }

    @GetMapping()
    public String showRoutes(Model model) {
        model.addAttribute(ROUTES_VARIABLE, ticketDAO.showRoutes());
        return ROUTES_PAGE;
    }

    @GetMapping("/{id}")
    public String getRoute(@PathVariable(PATH_VARIABLE_ID) int id, @ModelAttribute(ORDER_ATTRIBUTE) Order order, Model model) {
        model.addAttribute(TICKETS_VARIABLE, ticketDAO.getTickets(id));
        return TICKETS_PAGE;
    }

    @PostMapping("/{id}")
    public String makeOrder(@ModelAttribute(ORDER_ATTRIBUTE) @Valid Order order,
                            BindingResult bindingResult, Model model, @PathVariable(PATH_VARIABLE_ID) int id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(TICKETS_VARIABLE, ticketDAO.getTickets(id));
            return "routes/tickets";
        }
        orderDAO.addOrder(order);
        return ROOT_PAGE_REDIRECT;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute(ORDERS_ATTRIBUTE, orderDAO.loadAccountOrders());
        return PROFILE_PAGE;
    }

    @DeleteMapping("profile/{id}")
    public String delete(@PathVariable(PATH_VARIABLE_ID) int id) {
        orderDAO.deleteOrder(id);
        return PROFILE_PAGE_REDIRECT;
    }
}
