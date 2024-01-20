package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;

import java.util.Comparator;
import java.util.List;

public class FindOrderOrderedUseCase {

    private final OrderRepositoryPortOut orderRepositoryPortOut;
    public FindOrderOrderedUseCase(OrderRepositoryPortOut orderRepositoryPortOut) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
    }

    public List<OrderDto> findOrderOrdered() {
        List<OrderDto> orderDtoList = orderRepositoryPortOut.findOrderOrdered();

        return orderDtoList.stream().sorted(Comparator.comparing(OrderDto::orderStatus)).toList();
    }
}
