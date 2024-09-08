package org.example.grepp.controller.order;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.grepp.controller.order.request.OrderRegisterRequest;
import org.example.grepp.controller.order.request.OrderUpdateRequest;
import org.example.grepp.model.entity.order.Order;
import org.example.grepp.model.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j  // 로그 사용
@RequiredArgsConstructor
@RequestMapping("/order")
@RestController
public class OrderController {

    private final OrderService orderService;


    // 사용자가 주문한 목록 조회
    @GetMapping("/user/{email}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String email) {

        return ResponseEntity.ok().body(orderService.getUserOrders(email));
    }

    // 주문 등록
    @PostMapping("/")
    public ResponseEntity<Void> registerOrder(@Valid @RequestBody OrderRegisterRequest request) {

        orderService.registerOrder(request);
        return ResponseEntity.ok().build();
    }

    // 주문 메뉴 수정하기
    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrderMenu(@PathVariable Long orderId,
                                                @Valid @RequestBody OrderUpdateRequest request) {

        orderService.updateOrderMenu(orderId, request);
        return ResponseEntity.ok().build();
    }


    // 주문 삭제
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {

        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    // 관리자가 모든 주문 목록 조회
    @GetMapping("/admin")
    public ResponseEntity<List<Order>> getAdminOrders() {

        return ResponseEntity.ok().body(orderService.getAdminOrders());
    }

    // 관리자의 배송 시작 일괄 처리
    @PostMapping("/admin")
    public ResponseEntity<Void> startShipping() {

        orderService.startShipping();
        return ResponseEntity.ok().build();
    }

}