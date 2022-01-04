package com.spring.booking.controllers;

import com.spring.booking.exceptions.WrongArgumentException;
import com.spring.booking.models.Order;
import com.spring.booking.models.responses.ReportResponse;
import com.spring.booking.service.AccountService;
import com.spring.booking.service.OrderService;
import com.spring.booking.service.WriterService;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.spring.booking.constants.ServiceConstants.*;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {

    private final OrderService orderService;
    private final WriterService writerService;
    private final AccountService accountService;

    public AnalysisController(OrderService orderService, WriterService writerService, AccountService accountService) {
        this.orderService = orderService;
        this.writerService = writerService;
        this.accountService = accountService;
    }

    @GetMapping("/orders")
    public List<Order> getMonthOrders(@RequestParam(value = "date") String date) {
        return orderService.getMonthOrders(parseDate(date));
    }

    @GetMapping("/report")
    public List<ReportResponse> getMonthReport(@RequestParam(value = "date") String date) {
        return accountService.getReport(parseDate(date));
    }

    @GetMapping("/table")
    public Resource getOrdersTable() {
        writerService.writeTable();
        return writerService.loadAsResource();
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(date);
        } catch (ParseException e) {
            throw new WrongArgumentException("wrong date format");
        }
    }
}
