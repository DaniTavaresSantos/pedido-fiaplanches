package br.com.fiaplanchesorder.application.dtos;

import br.com.fiaplanchesorder.domain.enums.OrderStatus;

public record UpdatePaymentOrderDto(
        Long orderId,
        OrderStatus status
) {
}
