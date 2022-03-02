package com.example.bitfactory.product;

import com.example.bitfactory.infrastructure.Resource;
import com.example.bitfactory.product.command.CreateProductCommand;
import com.example.bitfactory.product.command.UpdateProductCommand;
import com.example.bitfactory.product.representation.ProductRepresentation;
import com.example.bitfactory.product.representation.ProductsRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public Resource<Void> addProduct(@RequestBody @Valid CreateProductCommand command) {
        productApplicationService.addProduct(command);
        return Resource.empty();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @GetMapping
    public Resource<ProductsRepresentation> getAllProducts() {
        return Resource.of(productApplicationService.getAllProducts());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/{id}")
    public Resource<ProductRepresentation> getProductById(@PathVariable Long id) {
        return Resource.of(productApplicationService.getProductById(id));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Resource<Void> updateProduct(@PathVariable Long id, @Valid @RequestBody UpdateProductCommand command) {
        productApplicationService.updateProduct(id, command);
        return Resource.empty();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public Resource<Void> deleteProduct(@PathVariable Long id) {
        productApplicationService.deleteProduct(id);
        return Resource.empty();
    }
}
