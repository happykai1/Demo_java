package com.example.demo.core;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESSFUL("DEMO-0000", "Successful"),

    // authorized & validate request
    INVALID_REQUEST_PARAMETER("DEMO-0001", "Invalid request parameter: "),
    INVALID_ROLE_ID("DEMO-0002","Invalid role id"),
    INVALID_POSITION_ID("DEMO-0003","Invalid position id"),
    INVALID_ISSUE_ID("DEMO-0004","Invalid issue id"),
    INTERNAL_SERVER_ERROR("DEMO-9999", "Internal server error");

    private final String code;
    private final String message;

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
}
