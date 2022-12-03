package com.mugi.uac.utils;

import lombok.Data;

@Data
public class RestResponseObject {

    public String message;
    private Object payload;
    private boolean requestStatus;

    public RestResponseObject() {
    }

    public RestResponseObject(boolean requestStatus, String message) {
        this.message = message;
        this.requestStatus = requestStatus;
    }

    public RestResponseObject(String message, Object payload, boolean requestStatus) {
        this.message = message;
        this.payload = payload;
        this.requestStatus = requestStatus;
    }

}
