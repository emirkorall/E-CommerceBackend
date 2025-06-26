package com.ecommerce.order;

import com.ecommerce.user.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  Optional<Order> findByIdAndUser(Long id, User user);

  List<Order> findAllByUser(User user);
}
