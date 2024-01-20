package br.com.fiaplanchesorder.application.ports.out;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.PageInfoDto;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPortOut {
    List<OrderDto> findOrderByCpf(String cpf, PageInfoDto pageInfoDto);
    List<OrderDto> findOrders(PageInfoDto page);
    OrderDto saveOrder(OrderDto pedidoDto);
    Optional<OrderDto> findOrderById(Long idPedido);
    List<OrderDto> findOrderOrdered();
}
