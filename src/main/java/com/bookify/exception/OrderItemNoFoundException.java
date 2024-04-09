package com.bookify.exception;

public class OrderItemNoFoundException extends Exception{

    public OrderItemNoFoundException(String message){
        super(message);
    }
}
