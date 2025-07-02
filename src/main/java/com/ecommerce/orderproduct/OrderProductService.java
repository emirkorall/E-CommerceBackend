package com.ecommerce.orderproduct;

import java.util.List;

public interface OrderProductService {

  List<OrderProduct> findAllOrderProducts();

  OrderProduct findOrderProductById(Long id);

  OrderProduct saveOrderProduct(OrderProduct orderProduct);

  OrderProduct deleteOrderProductById(Long id);

  List<OrderProduct> findOrderProductsByOrderId(Long orderId);

  List<OrderProduct> findOrderProductsByProductId(Long productId);
}
