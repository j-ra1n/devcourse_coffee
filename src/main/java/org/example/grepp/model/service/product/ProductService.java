package org.example.grepp.model.service.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.grepp.common.exception.ExceptionMessage;
import org.example.grepp.common.exception.product.ProductException;
import org.example.grepp.controller.product.request.ProductRegisterRequest;
import org.example.grepp.controller.product.request.ProductUpdateRequest;
import org.example.grepp.model.entity.product.Product;
import org.example.grepp.model.repository.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품 전체조회
    public List<Product> readProductList() {

        return productRepository.findAll();
    }

    // 상품 등록
    @Transactional
    public void registerProduct(ProductRegisterRequest request) {

        // 상품 저장
        Product product = Product.builder()
                .productName(request.getProductName())
                .category(request.getCategory())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();
        productRepository.save(product);
    }


    // 상품 수정
    @Transactional
    public void updateProduct(Long productId, ProductUpdateRequest request) {

        // 상품 조회
        Product product = findByIdOrThrowProductException(productId);

        product.updateProduct(
                request.getProductName(),
                request.getCategory(),
                request.getPrice(),
                request.getDescription());
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long productId) {

        // 상품 조회
        Product product = findByIdOrThrowProductException(productId);

        productRepository.delete(product);
    }

    // 카테고리별 상품 조회
    public List<Product> readProductsByCategory(String category) {

        return productRepository.findByCategory(category);
    }



    // 상품 조회 예외처리
    public Product findByIdOrThrowProductException(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.warn(">>>> {} : {} <<<<", productId, ExceptionMessage.PRODUCT_NOT_FOUND);
                    return new ProductException(ExceptionMessage.PRODUCT_NOT_FOUND);
                });
    }

}
