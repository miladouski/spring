package com.spring.booking.controllers;

import com.spring.booking.models.Account;
import com.spring.booking.models.Order;
import com.spring.booking.service.AccountService;
import com.spring.booking.service.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final AccountService accountService;
    private final OrderService orderService;

    public ProfileController(AccountService accountService, OrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return orderService.getAccountOrders();
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/photo")
    public void changePhoto(@RequestBody byte[] photo) {
        accountService.changePhoto(photo);
    }

    @PutMapping("/edit")
    public void editProfile(@Validated @RequestBody Account account) {
        accountService.editProfile(account);
    }
}
