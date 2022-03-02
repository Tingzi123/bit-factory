package com.example.bitfactory.product.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class CreateProductCommand {
    @NotBlank(message = "Name must not be empty")
    private String name;
    private String description;
    private BigDecimal price;
    private String link;
    private Long stock;
}
