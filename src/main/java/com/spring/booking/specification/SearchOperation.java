package com.spring.booking.specification;

public enum SearchOperation {

    EQUALITY, GREATER_THAN, LESS_THAN, LIKE;

    public static SearchOperation getOperation(String input) {
        switch (input) {
            case ":":
                return EQUALITY;
            case ">":
                return GREATER_THAN;
            case "<":
                return LESS_THAN;
            case "~":
                return LIKE;
            default:
                return null;
        }
    }
}
