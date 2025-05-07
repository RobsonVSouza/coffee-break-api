package com.coffeebreak.domain.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdesRepository extends JpaRepository <Orders, Long> {

    Optional<Orders> findByTicketId(Long aLong);
}
