package com.ecommerce.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecommerce.user.User;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
} 