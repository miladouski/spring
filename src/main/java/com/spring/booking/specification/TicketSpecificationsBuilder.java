package com.spring.booking.specification;

import com.spring.booking.models.Ticket;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketSpecificationsBuilder {
    
    private final List<SearchCriteria> criteria;

    public TicketSpecificationsBuilder() {
        criteria = new ArrayList<SearchCriteria>();
    }

    public void with(String key, SearchOperation operation, Object value) {
        criteria.add(new SearchCriteria(key, operation, value));
    }

    public Specification<Ticket> build() {
        if (criteria.size() == 0) {
            return null;
        }
        List<Specification> specifications = criteria.stream()
                .map(TicketSpecification::new)
                .collect(Collectors.toList());
        Specification result = specifications.get(0);
        for (int i = 1; i < criteria.size(); i++) {
            result = Specification.where(result)
                    .and(specifications.get(i));
        }
        return result;
    }


}
