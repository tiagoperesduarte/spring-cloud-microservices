package com.ecommerce.orderservice.exception;

public class OutOfStockException extends BadRequestException {
    public OutOfStockException(String message) {
        super(message);
    }
}