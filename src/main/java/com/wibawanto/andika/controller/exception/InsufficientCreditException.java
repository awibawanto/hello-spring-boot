package com.wibawanto.andika.controller.exception;

import com.wibawanto.andika.model.User;

/**
 * @author andika
 */
public class InsufficientCreditException extends Exception {
    private User user;
    private static final String DEFAULT_MESSAGE = "Not enough credit in account";

    public InsufficientCreditException() {
        super(DEFAULT_MESSAGE);
    }

    public InsufficientCreditException(User user) {
        super("Not enough credit in " + user.getName() + " account. Only " + user.getCreditAmount() + " left");

        this.user = user;
    }
}
