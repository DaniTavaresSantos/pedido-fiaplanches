package br.com.fiaplanchesorder.infra.config.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${rest.clients.endpoint}")
    private String endpointClient;

    @Value("${rest.products.endpoint}")
    private String endpointProduct;

    @Bean
    public WebClient restClient(WebClient.Builder builder) {
        return builder
                .baseUrl(endpointClient)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Bean
    public WebClient restProduct(WebClient.Builder builder) {
        return builder
                .baseUrl(endpointProduct)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
}
