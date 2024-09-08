package org.example.grepp.model.repository.orderitem;


import org.example.grepp.model.entity.order.Order;
import org.example.grepp.model.entity.orderitem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    // 사용자의 이메일을 기준으로 주문 내역을 조회하는 메서드
    List<OrderItem> findByOrder(Order order);
}