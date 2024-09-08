package org.example.grepp.controller.order.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.grepp.controller.orderitem.request.OrderItemRequest;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRegisterRequest {

    @NotNull
    @Size(max = 50, message = "이메일 50자 이내")
    private String email;

    @NotNull
    @Size(max = 200, message = "주소 200자 이내")
    private String address;

    @NotNull
    @Size(max = 200, message = "코드 200자 이내")
    private String postcode;

    @NotNull
    private List<OrderItemRequest> orderItems;
}
