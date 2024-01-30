package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.PageInfoDto;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.domain.enums.OrderStatus;

import java.util.List;

public class FindOrderStatusUseCase {

    private final OrderRepositoryPortOut orderRepositoryPortOut;

    public FindOrderStatusUseCase(OrderRepositoryPortOut orderRepositoryPortOut) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
    }

    public List<OrderDto> findOrderStatus(OrderStatus orderStatus) {
        return orderRepositoryPortOut.findOrderByStatus(orderStatus);
    }
}
