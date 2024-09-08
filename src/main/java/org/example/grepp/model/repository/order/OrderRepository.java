package org.example.grepp.model.repository.order;

import org.example.grepp.model.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    // 사용자의 이메일을 기준으로 주문 내역을 조회하는 메서드
    List<Order> findByEmail(String email);

    // 특정 시간 범위 내의 주문 목록 조회 (createdAt 필드 기준으로 조회)
    List<Order> findOrdersByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
