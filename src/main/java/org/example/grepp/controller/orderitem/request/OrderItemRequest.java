package org.example.grepp.controller.orderitem.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.grepp.model.entity.product.Product;

import java.util.UUID;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

    @NotNull
    private Long productId;

    @NotNull
    private int quantity;
}
