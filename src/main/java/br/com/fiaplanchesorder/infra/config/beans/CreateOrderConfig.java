package br.com.fiaplanchesorder.infra.config.beans;

import br.com.fiaplanchesorder.application.ports.out.ClientRestPortOut;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.ports.out.ProductRestPortOut;
import br.com.fiaplanchesorder.application.usecases.CreateOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateOrderConfig {

    @Bean
    public CreateOrderUseCase createOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut,
                                                 ClientRestPortOut clientRestPortOut,
                                                 ProductRestPortOut productRestPortOut) {
        return new CreateOrderUseCase(orderRepositoryPortOut, clientRestPortOut, productRestPortOut);
    }
}
