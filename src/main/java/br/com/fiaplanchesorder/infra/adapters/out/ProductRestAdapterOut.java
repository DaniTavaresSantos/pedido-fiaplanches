package br.com.fiaplanchesorder.infra.adapters.out;

import br.com.fiaplanchesorder.application.dtos.ProductDto;
import br.com.fiaplanchesorder.application.ports.out.ProductRestPortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRestAdapterOut implements ProductRestPortOut {

    @Autowired
    private WebClient restProduct;

    @Override
    public Optional<List<ProductDto>> findByIds(List<Long> productsIds) {

        List<ProductDto> productDtoList = restProduct.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/find/ids")
                        .queryParam("ids", productsIds)
                        .build()
                )
                .retrieve()
                .bodyToFlux(ProductDto.class)
                .collectList()
                .block();

        return Optional.of(productDtoList);
    }
}
