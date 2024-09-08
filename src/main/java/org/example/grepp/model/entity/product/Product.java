package org.example.grepp.model.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.grepp.model.entity.BaseEntity;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 객체 생성 못하도록 제한
@Entity(name = "PRODUCTS")
@Table(name = "PRODUCTS")
public class Product extends BaseEntity {

    @Id
    @Column(name = "PRODUCT_ID", columnDefinition = "BINARY(16)", nullable = false)
    private UUID productId;

    @Column(name = "PRODUCT_NAME", length = 20, nullable = false)
    private String productName;

    @Column(name = "CATEGORY", length = 50, nullable = false)
    private String category;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Builder
    public Product(UUID productId, String productName, String category, int price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }

    public void updateProduct(String productName, String category, int price, String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
    }
}