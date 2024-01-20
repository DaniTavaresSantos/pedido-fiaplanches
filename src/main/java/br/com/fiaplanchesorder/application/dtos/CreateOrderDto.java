package br.com.fiaplanchesorder.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record CreateOrderDto(
        @NotBlank(message = "CPF nao pode ser vazio")
        @CPF(message = "CPF informado e invalido")
        String cpf,
        @NotNull(message = "Produtos nao pode ser nulus")
        List<Long> produtos
) {

}
