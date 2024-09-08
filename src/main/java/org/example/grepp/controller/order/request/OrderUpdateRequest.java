package org.example.grepp.controller.order.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.example.grepp.controller.orderitem.request.OrderItemRequest;

import java.util.List;

@Getter
public class OrderUpdateRequest {

    @NotNull
    private List<OrderItemRequest> updatedOrderItems;
}