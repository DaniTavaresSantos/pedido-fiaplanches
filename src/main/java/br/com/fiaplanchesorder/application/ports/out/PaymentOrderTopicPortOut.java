package br.com.fiaplanchesorder.application.ports.out;

import br.com.fiaplanchesorder.application.dtos.PaymentOrderDto;

public interface PaymentOrderTopicPortOut {

    void sendPaymentOrder(PaymentOrderDto paymentOrderDto);
}
