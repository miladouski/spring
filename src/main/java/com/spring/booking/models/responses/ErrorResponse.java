package com.spring.booking.models.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorResponse {

    private Date timestamp;
    private int code;
    private String status;
    private String message;

    public ErrorResponse() {
        timestamp = new Date();
    }

    public ErrorResponse(int code, String status, String message) {
        this();
        this.code = code;
        this.status = status;
        this.message = message;
    }

}
