package br.com.fiaplanchesorder.infra.adapters.out;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.PageInfoDto;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.infra.repository.PostGresOrderRepository;
import br.com.fiaplanchesorder.infra.repository.entity.OrderEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderRepositoryAdapterOut implements OrderRepositoryPortOut {

    private final PostGresOrderRepository postGresOrderRepository;

    public OrderRepositoryAdapterOut(PostGresOrderRepository postGresOrderRepository) {
        this.postGresOrderRepository = postGresOrderRepository;
    }

    @Override
    public List<OrderDto> findOrderByCpf(String cpf, PageInfoDto pageInfoDto) {
        Pageable pageable = PageRequest.of(pageInfoDto.getPageNumber(), pageInfoDto.getPageSize());
        return postGresOrderRepository.findAllByCpf(cpf, pageable).stream().map(OrderEntity::toOrderDto).toList();
    }

    @Override
    public List<OrderDto> findOrders(PageInfoDto page) {
        Pageable pageable = PageRequest.of(page.getPageNumber(), page.getPageSize());
        return postGresOrderRepository.findAll(pageable).stream().map(OrderEntity::toOrderDto).toList();
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDto) {
        OrderEntity order = postGresOrderRepository.save(OrderEntity.toOrderEntity(orderDto));
        return order.toOrderDto();
    }

    @Override
    public Optional<OrderDto> findOrderById(Long idPedido) {
        return postGresOrderRepository.findById(idPedido).map(OrderEntity::toOrderDto);
    }

    @Override
    public List<OrderDto> findOrderOrdered() {
        return postGresOrderRepository.findAll().stream().map(OrderEntity::toOrderDto).toList();
    }
}