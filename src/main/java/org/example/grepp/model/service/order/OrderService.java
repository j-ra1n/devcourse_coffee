package org.example.grepp.model.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.grepp.common.exception.ExceptionMessage;
import org.example.grepp.common.exception.order.OrderException;
import org.example.grepp.controller.order.request.OrderRegisterRequest;
import org.example.grepp.controller.order.request.OrderUpdateRequest;
import org.example.grepp.controller.orderitem.request.OrderItemRequest;
import org.example.grepp.model.entity.order.Order;
import org.example.grepp.model.entity.order.constant.OrderStatus;
import org.example.grepp.model.entity.orderitem.OrderItem;
import org.example.grepp.model.entity.product.Product;
import org.example.grepp.model.repository.order.OrderRepository;
import org.example.grepp.model.repository.orderitem.OrderItemRepository;
import org.example.grepp.model.service.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.example.grepp.model.entity.order.constant.OrderStatus.PENDING;
import static org.example.grepp.model.entity.order.constant.OrderStatus.SHIPPING;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    // 사용자가 주문한 목록 조회
    public List<Order> getUserOrders(String email) {

        return orderRepository.findByEmail(email);
    }

    // 주문
    @Transactional
    public void registerOrder(OrderRegisterRequest request) {

        // 주문 저장
        Order order = Order.builder()
                .email(request.getEmail())
                .address(request.getAddress())
                .postcode(request.getPostcode())
                .orderStatus(PENDING)
                .build();
        orderRepository.save(order);

        for (OrderItemRequest itemRequest : request.getOrderItems()) {

            // 상품 조회
            Product product = productService.findByIdOrThrowProductException(itemRequest.getProductId());

            // OrderItem 생성
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .build();
            orderItemRepository.save(orderItem);
        }
    }


    // 주문 메뉴 수정하기
    @Transactional
    public void updateOrderMenu(Long orderId, OrderUpdateRequest request) {

        // 주문 조회
        Order order = findByIdOrThrowOrderException(orderId);

        // 주문 상태가 배송중 or 배송완료 예외처리
        if (!order.getOrderStatus().equals(PENDING)) {
            throw new OrderException(ExceptionMessage.ORDER_STATUS_NOT_FENDING);
        }

        // 기존 주문 항목 삭제
        List<OrderItem> existingOrderItems = orderItemRepository.findByOrder(order);
        orderItemRepository.deleteAll(existingOrderItems);

        // 새로운 주문 항목 저장
        for (OrderItemRequest itemRequest : request.getUpdatedOrderItems()) {

            // 상품 조회
            Product product = productService.findByIdOrThrowProductException(itemRequest.getProductId());

            // 새로운 OrderItem 생성 및 저장
            OrderItem updatedOrderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .category(product.getCategory())
                    .price(product.getPrice())
                    .quantity(itemRequest.getQuantity())
                    .build();
            orderItemRepository.save(updatedOrderItem);
        }
    }

    // 주문 취소
    @Transactional
    public void deleteOrder(Long orderId) {

        // 주문 조회
        Order order = findByIdOrThrowOrderException(orderId);

        // 주문 상태가 배송중 or 배송완료 예외처리
        if (!order.getOrderStatus().equals(PENDING)) {
            throw new OrderException(ExceptionMessage.ORDER_STATUS_NOT_FENDING);
        }

        orderRepository.delete(order);
    }

    // 관리자가 모든 주문 목록 조회
    public List<Order> getAdminOrders() {

        return orderRepository.findAll();
    }

    // 배송 시작 일괄 처리
    @Transactional
    public void startShipping() {

        // 전날 오후 2시부터 금일 오후 1시 59분까지의 주문 조회
        LocalDateTime yesterdayAfternoon = LocalDateTime.now().minusDays(1).with(LocalTime.of(14, 0));
        LocalDateTime todayBefore2pm = LocalDateTime.now().with(LocalTime.of(13, 59));

        List<Order> orders = orderRepository.findOrdersByCreatedAtBetween(yesterdayAfternoon, todayBefore2pm);

        for (Order order : orders) {
            if (order.getOrderStatus().equals(PENDING)) {
                // 주문 상태를 '배송중'으로 변경
                order.updateOrderStatus(SHIPPING);
            }
        }
    }



    // 주문 조회 예외처리
    public Order findByIdOrThrowOrderException(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> {
                    log.warn(">>>> {} : {} <<<<", orderId, ExceptionMessage.ORDER_NOT_FOUND);
                    return new OrderException(ExceptionMessage.ORDER_NOT_FOUND);
                });
    }
}
