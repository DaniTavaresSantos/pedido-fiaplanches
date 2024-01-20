package br.com.fiaplanchesorder.infra.adapters.out;

import br.com.fiaplanchesorder.application.dtos.PaymentOrderDto;
import br.com.fiaplanchesorder.application.ports.out.PaymentOrderTopicPortOut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentOrderTopicAdapterOut implements PaymentOrderTopicPortOut {

    private final KafkaTemplate<String, PaymentOrderDto> kafkaTemplatePaymentOrder;

    public PaymentOrderTopicAdapterOut(KafkaTemplate<String, PaymentOrderDto> kafkaTemplatePaymentOrder) {
        this.kafkaTemplatePaymentOrder = kafkaTemplatePaymentOrder;
    }

    @Override
    public void sendPaymentOrder(PaymentOrderDto paymentOrderDto) {
        kafkaTemplatePaymentOrder.send("fiap-lanches-payment", UUID.randomUUID().toString(), paymentOrderDto);
    }
}
