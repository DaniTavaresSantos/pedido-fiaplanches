package br.com.fiaplanchesorder.infra.config.beans;

import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.usecases.FindOrderClientUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindOrderClientConfig {

    @Bean
    public FindOrderClientUseCase findOrderClientUseCase(OrderRepositoryPortOut orderRepositoryPortOut) {
        return new FindOrderClientUseCase(orderRepositoryPortOut);
    }
}
