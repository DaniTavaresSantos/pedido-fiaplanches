package br.com.fiaplanchesorder.domain;

import br.com.fiaplanchesorder.application.dtos.PaymentOrderDto;
import br.com.fiaplanchesorder.domain.enums.PaymentMethodEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrder {

    private Long orderId;
    private PaymentMethodEnum paymentMethod;
    private BigDecimal value;

    public PaymentOrderDto toPaymentOrderDto() {
        return new PaymentOrderDto(
                this.orderId,
                this.paymentMethod,
                this.value
        );
    }
}
