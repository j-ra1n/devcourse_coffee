package org.example.grepp.common.exception.order;

import org.example.grepp.common.exception.CoffeeException;
import org.example.grepp.common.exception.ExceptionMessage;

public class OrderException extends CoffeeException{
    public OrderException(ExceptionMessage message) {
        super(message.getText());
    }
}
