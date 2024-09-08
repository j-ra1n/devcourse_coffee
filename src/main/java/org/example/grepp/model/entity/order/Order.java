package org.example.grepp.model.entity.order;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.grepp.model.entity.BaseEntity;
import org.example.grepp.model.entity.order.constant.OrderStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 외부에서 객체 생성 못하도록 제한
@Entity(name = "ORDERS")
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    @Id
    @Column(name = "ORDER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "EMAIL", length = 50, nullable = false)
    private String email;

    @Column(name = "ADDRESS", length = 200, nullable = false)
    private String address;

    @Column(name = "POSTCODE", length = 200, nullable = false)
    private String postcode;

    @Enumerated(EnumType.STRING)
    @Column(name = "ORDER_STATUS", length = 50, nullable = false)
    private OrderStatus orderStatus;

    @Builder
    public Order(Long orderId, String email, String address, String postcode, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
    }

    // 주문상태 업데이트
    public void updateOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}