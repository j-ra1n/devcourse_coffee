package org.example.grepp.common.exception.product;

import org.example.grepp.common.exception.CoffeeException;
import org.example.grepp.common.exception.ExceptionMessage;

public class ProductException extends CoffeeException {
    public ProductException(ExceptionMessage message) {
        super(message.getText());
    }
}
