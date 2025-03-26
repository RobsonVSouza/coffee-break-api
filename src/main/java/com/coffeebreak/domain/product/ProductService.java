package com.coffeebreak.domain.product;

import com.coffeebreak.domain.product.dto.ProductDTO;
import com.coffeebreak.exception.ProductNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;



    public Product save(ProductDTO dto) {
        if (dto.name() != null && productRepository.findByName(dto.name()).isPresent()) {
            throw new ProductNotFoundException("Produto já foi cadastrado");
        }
        Product productData = new Product(dto);
        return productRepository.save(productData);
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
    }

    public ProductDTO findById(Long id){
        return productRepository.findById(id)
                .map(ProductDTO::new)
                .orElseThrow(() -> new ProductNotFoundException("Produto com Id " + id + " não encontrado"));
    }

    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto com Id " + id + " não encontrado"));

        BeanUtils.copyProperties(productDTO, existingProduct, "id");
        return new ProductDTO(productRepository.save(existingProduct));
    }

    public void delete(Long id){
        findById(id);
        productRepository.deleteById(id);
    }

}
