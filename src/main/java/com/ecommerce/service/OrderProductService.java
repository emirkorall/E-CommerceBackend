package com.ecommerce.service;

import com.ecommerce.entity.OrderProduct;

import java.util.List;
import java.util.Optional;

public interface OrderProductService {

    List<OrderProduct> findAllOrderProducts();

    OrderProduct findOrderProductById(Long id);

    OrderProduct saveOrderProduct(OrderProduct orderProduct);

    OrderProduct deleteOrderProductById(Long id);

    List<OrderProduct> findOrderProductsByOrderId(Long orderId);

    List<OrderProduct> findOrderProductsByProductId(Long productId);
}
