package com.coffeebreak.domain.product.dto;

import com.coffeebreak.domain.product.Category;
import com.coffeebreak.domain.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record ProductDTO(Long id, String name, String description, Category category, List<String> pictures, BigDecimal price) {

    public ProductDTO(Product product) {
        this (product.getId(), product.getName(), product.getDescription(), product.getCategory(),product.getPictures(), product.getPrice());
    }
}
