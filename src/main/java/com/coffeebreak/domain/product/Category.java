package com.coffeebreak.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Category {

    COFFEE("Café", "Bebidas quentes à base de café"),
    TEA("Chá", "Infusões e chás naturais"),
    PASTRY("Confeitaria", "Bolos, tortas e doces");

    private final String name;
    private final String description;

}
