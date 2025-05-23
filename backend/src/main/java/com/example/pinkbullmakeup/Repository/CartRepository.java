package com.example.pinkbullmakeup.Repository;

import com.example.pinkbullmakeup.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByCustomer_UserId(UUID userId);
}
