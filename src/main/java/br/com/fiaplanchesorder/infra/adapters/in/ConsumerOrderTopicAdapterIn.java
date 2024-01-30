package br.com.fiaplanchesorder.infra.adapters.in;

import br.com.fiaplanchesorder.application.dtos.UpdatePaymentOrderDto;
import br.com.fiaplanchesorder.application.ports.in.UpdateOrderStatusPortIn;
import br.com.fiaplanchesorder.domain.UpdatePaymentOrder;
import br.com.fiaplanchesorder.infra.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerOrderTopicAdapterIn {

    private final UpdateOrderStatusPortIn updateOrderStatusPortIn;

    private final PaymentMapper paymentMapper;

    public ConsumerOrderTopicAdapterIn(UpdateOrderStatusPortIn updateOrderStatusPortIn, PaymentMapper paymentMapper) {
        this.updateOrderStatusPortIn = updateOrderStatusPortIn;
        this.paymentMapper = paymentMapper;
    }

    @KafkaListener(topics = "fiap-lanches-order", groupId = "fiap-lanches-order", containerFactory = "kafkaListenerContainerFactoryOrder")
    private void subscriber(ConsumerRecord<String, UpdatePaymentOrder> updatePaymentOrderConsumerRecord) {
        log.info("Iniciando atualização do status do pedido");
        UpdatePaymentOrderDto updatePaymentOrderDto = paymentMapper.updatePaymentOrderToUpdatePaymentOrderDto(updatePaymentOrderConsumerRecord.value());
        updateOrderStatusPortIn.updateOrderStatus(updatePaymentOrderDto);
        log.info("Pedido {} atualizado com sucesso", updatePaymentOrderDto.orderId());
    }
}
