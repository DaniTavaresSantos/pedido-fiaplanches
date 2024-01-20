package br.com.fiaplanchesorder.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record UpdateOrderDto(
        @NotNull(message = "Numero da ordem nao pode ser vazio")
        Long order,

        @NotNull(message = "Lista de produtos nao pode ser null")
        List<Long> products
) {
}
