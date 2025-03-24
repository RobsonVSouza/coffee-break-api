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

        Product product = new Product(
                null,
                dto.name(),
                dto.description(),
                dto.category(),
                dto.pictures(),
                dto.price()
        );

        return productRepository.save(product);
    }

    public List <ProductDTO> findAll(){
        List <Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getPictures(),
                        product.getPrice()
                ))
                .collect(Collectors.toList());
    }

    public ProductDTO findById(Long id){
        return productRepository.findById(id)
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getCategory(),
                        product.getPictures(),
                        product.getPrice()
                ))
                .orElseThrow(() -> new ProductNotFoundException("Produto com Id " + id + " não encontrado"));
    }

    public ProductDTO update(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produto com Id " + id + " não encontrado"));

        BeanUtils.copyProperties(productDTO, existingProduct, "id");

        Product updatedProduct = productRepository.save(existingProduct);

        return new ProductDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getDescription(),
                updatedProduct.getCategory(),
                updatedProduct.getPictures(),
                updatedProduct.getPrice()
        );
    }

    public void delete(Long id){
        if (!productRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado para deleção");
        }
        productRepository.deleteById(id);
    }

}
