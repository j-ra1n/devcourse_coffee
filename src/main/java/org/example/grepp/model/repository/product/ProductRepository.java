package org.example.grepp.model.repository.product;

import org.example.grepp.model.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 카테고리별로 상품을 조회하는 메서드
    List<Product> findByCategory(String category);
}
