package br.com.fiaplanchesorder.infra.config.beans;

import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.usecases.FindOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindOrderConfig {

    @Bean
    public FindOrderUseCase findOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut) {
        return new FindOrderUseCase(orderRepositoryPortOut);
    }
}
