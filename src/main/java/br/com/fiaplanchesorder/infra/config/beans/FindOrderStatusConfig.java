package br.com.fiaplanchesorder.infra.config.beans;

import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.usecases.FindOrderStatusUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindOrderStatusConfig {

    @Bean
    public FindOrderStatusUseCase findOrderStatusUseCase(OrderRepositoryPortOut orderRepositoryPortOut) {
        return new FindOrderStatusUseCase(orderRepositoryPortOut);
    }
}
