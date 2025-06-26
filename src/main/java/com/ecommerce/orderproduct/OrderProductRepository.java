package com.ecommerce.orderproduct;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

  List<OrderProduct> findByOrderId(Long orderId);

  List<OrderProduct> findByProductId(Long productId);
}
