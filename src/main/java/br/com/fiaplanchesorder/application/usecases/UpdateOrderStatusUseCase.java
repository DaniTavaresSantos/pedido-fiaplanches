package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.UpdatePaymentOrderDto;
import br.com.fiaplanchesorder.application.ports.in.UpdateOrderStatusPortIn;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.domain.Order;
import br.com.fiaplanchesorder.infra.exception.handler.OrderBusinessException;
import br.com.fiaplanchesorder.infra.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class UpdateOrderStatusUseCase implements UpdateOrderStatusPortIn{

    private final OrderRepositoryPortOut orderRepositoryPortOut;

    private final PaymentMapper paymentMapper;

    public UpdateOrderStatusUseCase(OrderRepositoryPortOut orderRepositoryPortOut, PaymentMapper paymentMapper) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
        this.paymentMapper = paymentMapper;
    }

    @Override
    @Transactional
    public void updateOrderStatus(UpdatePaymentOrderDto updatePaymentOrderDto) {
        log.info("Executando updateOrderStatus - UpdatePaymentOrderDto: {}", updatePaymentOrderDto);

        if (updatePaymentOrderDto.status() == null) {
            throw new OrderBusinessException("Status não pode ser nulo");
        }

        OrderDto orderDto = orderRepositoryPortOut.findOrderById(updatePaymentOrderDto.orderId())
                .orElseThrow(() -> new OrderBusinessException("Nao localizado pedido com o id: " + updatePaymentOrderDto.orderId()));

        Order order = paymentMapper.orderDtoToOrder(orderDto);

        order.setOrderStatus(updatePaymentOrderDto.status());
        order.setApproved(true);

        OrderDto orderDtoUpdated = paymentMapper.orderToOrderDto(order);

        orderRepositoryPortOut.saveOrder(orderDtoUpdated);

        log.info("updateOrderStatus finalizado com sucesso");
    }
}
