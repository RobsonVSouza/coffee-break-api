package com.coffeebreak.domain.product;

import com.coffeebreak.domain.product.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID =1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ElementCollection
    private List<String> pictures;

    private BigDecimal price;

    public Product(ProductDTO dto) {
        this.id = dto.id();
        this.name = dto.name();
        this.description = dto.description();
        this.category = dto.category();
        this.pictures = dto.pictures();
        this.price = dto.price();
    }



}
