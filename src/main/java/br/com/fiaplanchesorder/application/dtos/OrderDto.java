package br.com.fiaplanchesorder.application.dtos;

import br.com.fiaplanchesorder.domain.Order;
import br.com.fiaplanchesorder.domain.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        Long id,
        String cpf,
        List<Long> products,
        LocalDateTime dateOrder,
        OrderStatus orderStatus,
        Boolean isApproved
) {
    public OrderDto(Long id, String cpf, List<Long> products, LocalDateTime dateOrder, OrderStatus orderStatus, Boolean isApproved) {
        this.id = id;
        this.cpf = cpf;
        this.products = products;
        this.dateOrder = dateOrder;
        this.orderStatus = orderStatus;
        this.isApproved = isApproved;
    }

    public Order toOrder() {
        Order order = new Order();
        order.setId(this.id());
        order.setCpf(this.cpf());
        order.setProducts(this.products());
        order.setDateOrder(this.dateOrder());
        order.setOrderStatus(this.orderStatus());
        order.setApproved(this.isApproved());
        return order;
    }

    public static OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getCpf(),
                order.getProducts(),
                order.getDateOrder(),
                order.getOrderStatus(),
                order.getApproved()
        );
    }
}
