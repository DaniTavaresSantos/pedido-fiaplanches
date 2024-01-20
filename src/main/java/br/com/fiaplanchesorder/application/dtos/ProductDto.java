package br.com.fiaplanchesorder.application.dtos;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String nomeProduto,
        BigDecimal preco,
        String categoria
) {

}
