package com.example.bitfactory.product;

import com.example.bitfactory.infrastructure.exception.NotFoundException;
import com.example.bitfactory.product.command.CreateProductCommand;
import com.example.bitfactory.product.command.UpdateProductCommand;
import com.example.bitfactory.product.representation.ProductRepresentation;
import com.example.bitfactory.product.representation.ProductsRepresentation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductApplicationService {
    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void addProduct(CreateProductCommand command) {
        productRepository.save(ProductAssembler.toProduct(command));
    }

    public ProductsRepresentation getAllProducts() {
        return ProductAssembler.toProductsRepresentation(productRepository.findAll());
    }

    public ProductRepresentation getProductById(Long id) {
        return ProductAssembler.toProductRepresentation(productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found!")));
    }

    public void updateProduct(Long id, UpdateProductCommand command) {
        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found!"));
        product.update(ProductAssembler.toProduct(command));
        productRepository.save(product);
    }

    public void updateProductStock(Long id, Long num) {
        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found!"));
       /* if (product.getStock() < num) {
            throw new RuntimeException("Out of stock error");
        }*/
        product.updateStock(product.getStock() - num);
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found!"));
        productRepository.deleteById(id);
    }
}
