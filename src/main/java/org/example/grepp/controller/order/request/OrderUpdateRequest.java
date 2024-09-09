package org.example.grepp.controller.order.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.grepp.controller.orderitem.request.OrderItemRequest;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateRequest {

    @NotNull
    private List<OrderItemRequest> updatedOrderItems;
}