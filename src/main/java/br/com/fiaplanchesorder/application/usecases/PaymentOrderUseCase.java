package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.PaymentOrderDto;
import br.com.fiaplanchesorder.application.dtos.ProductDto;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.ports.out.PaymentOrderTopicPortOut;
import br.com.fiaplanchesorder.application.ports.out.ProductRestPortOut;
import br.com.fiaplanchesorder.domain.Order;
import br.com.fiaplanchesorder.domain.PaymentOrder;
import br.com.fiaplanchesorder.domain.enums.OrderStatus;
import br.com.fiaplanchesorder.infra.exception.handler.OrderBusinessException;

import java.math.BigDecimal;
import java.util.List;

public class PaymentOrderUseCase {

    private final OrderRepositoryPortOut orderRepositoryPortOut;

    private final PaymentOrderTopicPortOut paymentOrderTopicPortOut;

    private final ProductRestPortOut productRestPortOut;

    public PaymentOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut,
                               PaymentOrderTopicPortOut paymentOrderTopicPortOut, ProductRestPortOut productRestPortOut) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
        this.paymentOrderTopicPortOut = paymentOrderTopicPortOut;
        this.productRestPortOut = productRestPortOut;
    }

    public OrderDto payOrder(PaymentOrderDto paymentOrderDto) {

        OrderDto orderDto = orderRepositoryPortOut.findOrderById(paymentOrderDto.orderId())
                .orElseThrow(() -> new OrderBusinessException("Pedido não encontrado"));

        if (Boolean.TRUE.equals(orderDto.approved())) {
            throw new OrderBusinessException("Pedido aprovado");
        }

        List<ProductDto> productDtos = productRestPortOut.findByIds(orderDto.products())
                .orElseThrow(() -> new OrderBusinessException("Produtos não encontrados"));

        PaymentOrder paymentOrder = paymentOrderDto.toPaymentOrder();
        paymentOrder.setValue(calculaValorTotal(productDtos));

        paymentOrderTopicPortOut.sendPaymentOrder(paymentOrder.toPaymentOrderDto());

        Order order = orderDto.toOrder();
        order.setOrderStatus(OrderStatus.PAGAMENTO_PENDENTE);
        
        return orderRepositoryPortOut.saveOrder(OrderDto.toOrderDto(order));
    }

    private BigDecimal calculaValorTotal(List<ProductDto> produtos) {
        return produtos.stream().map(ProductDto::preco).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
