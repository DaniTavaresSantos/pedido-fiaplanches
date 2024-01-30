package br.com.fiaplanchesorder.infra.config.beans;

import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.usecases.UpdateOrderStatusUseCase;
import br.com.fiaplanchesorder.infra.mapper.PaymentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateOrderStatusConfig {

    @Bean
    public UpdateOrderStatusUseCase updateOrderStatusUseCase(OrderRepositoryPortOut orderRepositoryPortOut, PaymentMapper paymentMapper) {
        return new UpdateOrderStatusUseCase(orderRepositoryPortOut, paymentMapper);
    }
}
