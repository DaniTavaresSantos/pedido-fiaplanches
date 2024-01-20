package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.PageInfoDto;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;

import java.util.List;

public class FindOrderUseCase {

    private final OrderRepositoryPortOut orderRepositoryPortOut;

    public FindOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
    }

    public List<OrderDto> findOrders(PageInfoDto page) {
        return orderRepositoryPortOut.findOrders(page);
    }
}
