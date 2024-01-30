package br.com.fiaplanchesorder.infra.mapper;

import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.UpdatePaymentOrderDto;
import br.com.fiaplanchesorder.domain.Order;
import br.com.fiaplanchesorder.domain.UpdatePaymentOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    UpdatePaymentOrderDto updatePaymentOrderToUpdatePaymentOrderDto(UpdatePaymentOrder updatePaymentOrder);

    Order orderDtoToOrder(OrderDto orderDto);

    OrderDto orderToOrderDto(Order order);
}
