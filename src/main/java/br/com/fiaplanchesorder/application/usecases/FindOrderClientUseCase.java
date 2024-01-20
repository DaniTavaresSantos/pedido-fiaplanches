package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.PageInfoDto;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;

import java.util.List;

public class FindOrderClientUseCase {

    private final OrderRepositoryPortOut orderRepositoryPortOut;

    public FindOrderClientUseCase(OrderRepositoryPortOut orderRepositoryPortOut) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
    }

    public List<OrderDto> findOrderClient(String cpf, PageInfoDto pageInfoDto) {
        return orderRepositoryPortOut.findOrderByCpf(cpf, pageInfoDto);
    }
}
