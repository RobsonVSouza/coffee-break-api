package com.coffeebreak.domain.cart.dto;

import com.coffeebreak.domain.product.dto.ProductDTO;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long id,
        ProductDTO product,
        Long quantity,
        BigDecimal subtotal
) {
}