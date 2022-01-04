package com.spring.booking.repository;

import com.spring.booking.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer>, JpaSpecificationExecutor<Ticket> {

    @Query("SELECT t FROM Ticket t WHERE t.route.id = ?1")
    List<Ticket> findAllByRouteId(int id);
}
