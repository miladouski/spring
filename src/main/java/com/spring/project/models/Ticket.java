package com.spring.project.models;

import java.util.Date;

public class Ticket {
    private int id;
    private String route;
    private Date date;

    public Ticket(int id, String routeId, Date date) {
        this.id = id;
        this.route = routeId;
        this.date = date;
    }

    public Ticket(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
