package br.com.fiaplanchesorder.infra.repository.entity;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.domain.enums.OrderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tbl_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderProduct> orderProducts;

    @Column(name = "data_pedido")
    LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotNull
    private Boolean isApproved;

    public OrderDto toOrderDto() {
        return new OrderDto(
                this.id,
                this.cpf,
                this.orderProducts.stream().map(OrderProduct::getProductId).toList(),
                this.dataPedido,
                this.orderStatus,
                this.isApproved
        );
    }

    public static OrderEntity toOrderEntity(OrderDto orderDto) {
        return new OrderEntity(
                orderDto.id(),
                orderDto.cpf(),
                orderDto.products().stream().map(orderProduct -> new OrderProduct(null, orderProduct)).toList(),
                orderDto.dateOrder(),
                orderDto.orderStatus(),
                orderDto.approved()
        );
    }
}
