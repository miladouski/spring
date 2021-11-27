package com.spring.project.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static com.spring.project.constants.ConstantsModel.*;

public class Order {

    private int id;
    @NotNull(message = TICKET_NOT_NULL_MASSAGE)
    private Ticket ticket;
    @Min(value = AMOUNT_MIN_VALUE, message = AMOUNT_MIN_VALUE_MESSAGE)
    @Max(value = AMOUNT_MAX_VALUE, message = AMOUNT_MAX_VALUE_MESSAGE)
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
