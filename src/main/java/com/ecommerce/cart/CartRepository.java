package com.ecommerce.cart;

import com.ecommerce.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByUser(User user);
}
