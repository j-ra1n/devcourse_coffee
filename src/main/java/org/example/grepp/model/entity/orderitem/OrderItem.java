package org.example.grepp.model.entity.orderitem;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.grepp.model.entity.BaseEntity;
import org.example.grepp.model.entity.order.Order;
import org.example.grepp.model.entity.product.Product;
import org.example.grepp.model.entity.product.constant.Category;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ORDER_ITEMS")
@Table(name = "ORDER_ITEMS", indexes = {
        @Index(name = "idx_order_id", columnList = "ORDER_ID")
})
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "CATEGORY", length = 50, nullable = false)
    private Category category;

    @Column(name = "PRICE", nullable = false)
    private int price;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    @Builder
    public OrderItem(Order order, Product product, Category category, int price, int quantity) {
        this.order = order;
        this.product = product;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
}