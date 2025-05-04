package com.coffeebreak.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCartRepository extends JpaRepository <OrderCart, Long> {

}
