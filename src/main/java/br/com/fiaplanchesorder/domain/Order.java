package br.com.fiaplanchesorder.domain;

import br.com.fiaplanchesorder.domain.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {

    private Long id;

    private String cpf;

    private List<Long> products;

    private LocalDateTime dateOrder;

    private OrderStatus orderStatus;

    private Boolean approved = false;

    public Order() {
    }

    public Order(Long id, String cpf, List<Long> products, LocalDateTime dateOrder, OrderStatus orderStatus, Boolean approved) {
        this.id = id;
        this.cpf = cpf;
        this.products = products;
        this.dateOrder = dateOrder;
        this.orderStatus = orderStatus;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(cpf, order.cpf) && Objects.equals(products, order.products) && Objects.equals(dateOrder, order.dateOrder) && orderStatus == order.orderStatus && Objects.equals(approved, order.approved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, products, dateOrder, orderStatus, approved);
    }
}