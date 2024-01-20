package br.com.fiaplanchesorder.infra.config.beans;

import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.ports.out.ProductRestPortOut;
import br.com.fiaplanchesorder.application.usecases.UpdateOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateOrderConfig {

    @Bean
    public UpdateOrderUseCase updateOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut,
                                                 ProductRestPortOut productRestPortOut) {
        return new UpdateOrderUseCase(orderRepositoryPortOut, productRestPortOut);
    }
}
