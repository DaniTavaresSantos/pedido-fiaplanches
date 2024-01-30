package br.com.fiaplanchesorder.application.dtos;

import br.com.fiaplanchesorder.domain.PaymentOrder;
import br.com.fiaplanchesorder.domain.enums.PaymentMethodEnum;

import java.math.BigDecimal;

public record PaymentOrderDto(
        Long orderId,
        PaymentMethodEnum paymentMethod,
        BigDecimal value
) {
        public PaymentOrderDto(Long orderId, PaymentMethodEnum paymentMethod, BigDecimal value) {
                this.orderId = orderId;
                this.paymentMethod = paymentMethod;
                this.value = value;
        }

        public PaymentOrder toPaymentOrder() {
                return new PaymentOrder(
                        this.orderId,
                        this.paymentMethod,
                        this.value
                );
        }
}
