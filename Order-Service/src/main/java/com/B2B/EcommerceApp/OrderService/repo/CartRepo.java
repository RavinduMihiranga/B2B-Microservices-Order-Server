package com.B2B.EcommerceApp.OrderService.repo;

import com.B2B.EcommerceApp.OrderService.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findAllBySessionIDEquals(String sessionID);

    boolean existsBySessionID(String sessionID);

    boolean existsBySessionIDEqualsAndProductSyscoIDEquals(String sessionID, String productSyscoID);

    boolean existsBySessionIDEqualsAndCartIDEquals(String sessionID, int cartID);

    Cart findBySessionIDEqualsAndCartIDEquals(String sessionID, int cartID);

    Cart findAllBySessionIDEqualsAndProductSyscoIDEquals(String sessionID, String productSyscoID);
}
