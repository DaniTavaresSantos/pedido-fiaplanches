package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.ProductDto;
import br.com.fiaplanchesorder.application.dtos.UpdateOrderDto;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.ports.out.ProductRestPortOut;
import br.com.fiaplanchesorder.domain.Order;
import br.com.fiaplanchesorder.domain.enums.OrderStatus;
import br.com.fiaplanchesorder.infra.exception.handler.OrderBusinessException;
import jakarta.persistence.EntityNotFoundException;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.List;

public class UpdateOrderUseCase {

    private final OrderRepositoryPortOut orderRepositoryPortOut;

    private final ProductRestPortOut productRestPortOut;

    public UpdateOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut, ProductRestPortOut productRestPortOut) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
        this.productRestPortOut = productRestPortOut;
    }

    public OrderDto updateOrder(UpdateOrderDto updateOrderDto) {

        OrderDto orderOldDto = orderRepositoryPortOut.findOrderById(updateOrderDto.order()).orElseThrow(
                () -> new OrderBusinessException("Pedido não encontrado")
        );

        if (orderOldDto.orderStatus().equals(OrderStatus.NO_CARRINHO)) {
            List<Long> productsNew = updateOrderDto.products();
            List<Long> productsOld = orderOldDto.products();

            productRestPortOut.findByIds(productsNew).orElseThrow(
                    () -> new OrderBusinessException("Falha para consultar os novos produtos")
            );

            var idsMatch = new HashSet<>(productsNew).containsAll(productsOld);

            if (idsMatch) {
                throw new OrderBusinessException("Nao localizado alteracoes nos produtos informados");
            }

            Order order = orderOldDto.toOrder();
            order.setProducts(productsNew);

            OrderDto orderUpdateDto = OrderDto.toOrderDto(order);

            return orderRepositoryPortOut.saveOrder(orderUpdateDto);
        } else {
            throw new OrderBusinessException("Status do pedido invalido para atualizacao");
        }
    }
}
