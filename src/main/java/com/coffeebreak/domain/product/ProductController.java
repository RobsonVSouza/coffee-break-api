package com.coffeebreak.domain.product;

import com.coffeebreak.domain.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO dto){
        Product product = productService.save(dto);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity <ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        ProductDTO update = productService.update(id,productDTO);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
