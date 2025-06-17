package com.ecommerce.repository;

import com.ecommerce.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<OrderProduct> findByOrderId(Long orderId);

    List<OrderProduct> findByProductId(Long productId);


}
