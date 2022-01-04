package com.spring.booking.models.responses;

import java.util.Date;

public class ReportResponse {

    private String username;
    private String email;
    private Date regDate;
    private int ordersCount;

    public ReportResponse(String username, String email, Date regDate, int ordersCount) {
        this.username = username;
        this.email = email;
        this.regDate = regDate;
        this.ordersCount = ordersCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }
}
