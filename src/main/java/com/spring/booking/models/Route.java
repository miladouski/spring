package com.spring.booking.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    private int id;
    private String startPoint;
    private String endPoint;
    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    private Collection<Ticket> tickets;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
