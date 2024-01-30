package br.com.fiaplanchesorder.application.ports.in;

import br.com.fiaplanchesorder.application.dtos.UpdatePaymentOrderDto;

public interface UpdateOrderStatusPortIn {

    void updateOrderStatus(UpdatePaymentOrderDto updatePaymentOrderDto);
}
