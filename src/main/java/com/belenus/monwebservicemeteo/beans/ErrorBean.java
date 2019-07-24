package com.belenus.monwebservicemeteo.beans;

public class ErrorBean {
    private String message;
    private String cod;

    public String getMessage() {
        return message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
