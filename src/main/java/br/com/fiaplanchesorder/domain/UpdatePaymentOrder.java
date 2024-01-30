package br.com.fiaplanchesorder.domain;

import br.com.fiaplanchesorder.domain.enums.OrderStatus;

public class UpdatePaymentOrder {
    private Long orderId;
    private OrderStatus status;

    public UpdatePaymentOrder(Long orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }

    public UpdatePaymentOrder() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UpdatePaymentOrder{" +
                "orderId=" + orderId +
                ", status=" + status +
                '}';
    }
}
