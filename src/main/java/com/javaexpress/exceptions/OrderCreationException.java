package com.javaexpress.exceptions;

public class OrderCreationException extends RuntimeException{
   public OrderCreationException(String msg){
        super(msg);
    }
}
