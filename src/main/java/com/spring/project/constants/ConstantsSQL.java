package com.spring.project.constants;

public class ConstantsSQL {

    public static final String GET_ACCOUNT_QUERY = "SELECT * FROM accounts WHERE username=?";
    public static final String SAVE_ACCOUNT_QUERY = "INSERT INTO accounts (username, password) VALUES(?, ?)";
    public static final String ACCOUNT_ORDERS_QUERY = "SELECT orders.id, amount, tickets.id,route, date FROM orders INNER JOIN tickets ON ticketId = tickets.id INNER JOIN routes ON routes.id = tickets.routeId WHERE accountId = ?";
    public static final String ADD_ORDER_QUERY = "INSERT INTO orders (accountId, ticketId, amount) VALUES(?, ?, ?)";
    public static final String DELETE_ORDER_QUERY = "DELETE FROM orders WHERE id=? AND accountId=?";
    public static final String GET_ALL_ROUTES_QUERY = "SELECT routes.id, route FROM routes";
    public static final String GET_ROUTE_TICKETS = "SELECT tickets.id, route, date FROM tickets INNER JOIN routes ON routes.id = tickets.routeId WHERE routeId = ?";


    public static final String ORDERS_ID_COLUMN = "orders.id";
    public static final String TICKETS_ID_COLUMN = "tickets.id";
    public static final String ROUTES_ID_COLUMN = "routes.id";
    public static final String ROUTE_COLUMN = "route";
    public static final String DATE_COLUMN = "date";
    public static final String AMOUNT_COLUMN = "amount";


}
