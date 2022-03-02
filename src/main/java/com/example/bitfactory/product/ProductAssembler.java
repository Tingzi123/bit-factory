package com.example.bitfactory.product;

import com.example.bitfactory.product.command.CreateProductCommand;
import com.example.bitfactory.product.command.UpdateProductCommand;
import com.example.bitfactory.product.model.Product;
import com.example.bitfactory.product.representation.ProductRepresentation;
import com.example.bitfactory.product.representation.ProductsRepresentation;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductAssembler {
    public static Product toProduct(CreateProductCommand command) {
        return Product.builder()
                .name(command.getName())
                .description(command.getDescription())
                .price(command.getPrice())
                .link(command.getLink())
                .stock(command.getStock())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    public static Product toProduct(UpdateProductCommand command) {
        return Product.builder()
                .name(command.getName())
                .description(command.getDescription())
                .price(command.getPrice())
                .link(command.getLink())
                .stock(command.getStock())
                .build();
    }

    public static ProductsRepresentation.ProductVO toProductVO(Product product) {
        return ProductsRepresentation.ProductVO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .link(product.getLink())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static ProductsRepresentation toProductsRepresentation(List<Product> products) {
        return ProductsRepresentation.builder()
                .products(products.stream()
                        .map(ProductAssembler::toProductVO)
                        .collect(toList()))
                .build();
    }

    public static ProductRepresentation toProductRepresentation(Product product) {
        return ProductRepresentation.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .link(product.getLink())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
