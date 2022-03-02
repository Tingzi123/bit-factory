package com.example.bitfactory.order.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderCommand {
    @NotBlank(message = "num must not be empty")
    private Long num;
}
