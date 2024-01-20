package br.com.fiaplanchesorder.application.dtos;

import br.com.fiaplanchesorder.domain.PaymentOrder;
import br.com.fiaplanchesorder.domain.enums.PaymentMethodEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.Optional;

public record PaymentOrderDto(
        Long orderId,
        PaymentMethodEnum paymentMethod,
        BigDecimal paymentValue
) {
        public PaymentOrderDto(Long orderId, PaymentMethodEnum paymentMethod, BigDecimal paymentValue) {
                this.orderId = orderId;
                this.paymentMethod = paymentMethod;
                this.paymentValue = paymentValue;
        }

        public PaymentOrder toPaymentOrder() {
                return new PaymentOrder(
                        this.orderId,
                        this.paymentMethod,
                        this.paymentValue
                );
        }
}
