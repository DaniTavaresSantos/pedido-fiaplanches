package br.com.fiaplanchesorder.infra.config.beans;

import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.ports.out.PaymentOrderTopicPortOut;
import br.com.fiaplanchesorder.application.ports.out.ProductRestPortOut;
import br.com.fiaplanchesorder.application.usecases.PaymentOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentOrderConfig {

    @Bean
    public PaymentOrderUseCase paymentOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut,
                                             PaymentOrderTopicPortOut paymentOrderTopicPortOut,
                                                   ProductRestPortOut productRestPortOut) {
        return new PaymentOrderUseCase(orderRepositoryPortOut, paymentOrderTopicPortOut, productRestPortOut);
    }
}
