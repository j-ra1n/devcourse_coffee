package org.example.grepp.controller.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterRequest {

    @NotNull
    @Size(max = 20, message = "상품명 20자 이내")
    private String productName;

    @NotNull
    @Size(max = 50, message = "카테고리 50자 이내")
    private String category;

    @NotNull
    private int price;

    @Size(max = 500, message = "상품 설명 500자 이내")
    private String description;
}
