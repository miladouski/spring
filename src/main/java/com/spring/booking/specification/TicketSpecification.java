package com.spring.booking.specification;

import com.spring.booking.models.Ticket;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TicketSpecification implements Specification<Ticket> {

    private SearchCriteria criteria;

    public TicketSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    private Path getPath(Root root, String attributeName) {
        Path path = root;
        for (String part : attributeName.split("\\.")) {
            path = path.get(part);
        }
        return path;
    }

    @Override
    public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Path path = getPath(root, criteria.getKey());
        switch(criteria.getOperation()) {
            case EQUALITY:
                return criteriaBuilder.equal(path, criteria.getValue());
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(path, criteria.getValue().toString());
            case LESS_THAN:
                return criteriaBuilder.lessThan(path, criteria.getValue().toString());
            case LIKE:
                return criteriaBuilder.like(path, criteria.getValue().toString());
            default:
                return null;
        }
    }
}
