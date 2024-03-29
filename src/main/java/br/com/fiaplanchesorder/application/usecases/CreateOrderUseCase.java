package br.com.fiaplanchesorder.application.usecases;

import br.com.fiaplanchesorder.application.dtos.ClientDto;
import br.com.fiaplanchesorder.application.dtos.CreateOrderDto;
import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.ProductDto;
import br.com.fiaplanchesorder.application.ports.out.ClientRestPortOut;
import br.com.fiaplanchesorder.application.ports.out.OrderRepositoryPortOut;
import br.com.fiaplanchesorder.application.ports.out.ProductRestPortOut;
import br.com.fiaplanchesorder.domain.enums.OrderStatus;
import br.com.fiaplanchesorder.infra.exception.handler.OrderBusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class CreateOrderUseCase {

    private final OrderRepositoryPortOut orderRepositoryPortOut;

    private final ClientRestPortOut clientRestPortOut;

    private final ProductRestPortOut productRestPortOut;

    public CreateOrderUseCase(OrderRepositoryPortOut orderRepositoryPortOut,
                              ClientRestPortOut clientRestPortOut,
                              ProductRestPortOut productRestPortOut) {
        this.orderRepositoryPortOut = orderRepositoryPortOut;
        this.clientRestPortOut = clientRestPortOut;
        this.productRestPortOut = productRestPortOut;
    }

    public OrderDto createOrder(CreateOrderDto createOrderDto) {
        log.info("Executando adicionaNoCarrinho");

        log.info("Buscando cliente pelo cpf: {}", createOrderDto.cpf());
        clientRestPortOut.findCpf(createOrderDto.cpf())
                .orElseThrow(
                        () -> new OrderBusinessException("Cliente não encontrado")
                );
        log.info("Cliente encontrado com sucesso");

        List<Long> idProdutos = createOrderDto.produtos();

        log.info("Buscando produtos pelos ids: {}", idProdutos);
        List<ProductDto> productDtoList = productRestPortOut.findByIds(idProdutos)
                .orElseThrow(
                        () -> new OrderBusinessException("Produtos nao encontrados")
                );
        log.info("Produtos encontrados com sucesso");

        List<Long> idsProductsRegistered = productDtoList.stream().map(ProductDto::id).toList();

        log.info("Criando pedido com os produtos: {}", idsProductsRegistered);
        var pedidoDto = new OrderDto(
                null, createOrderDto.cpf(), idsProductsRegistered, LocalDateTime.now(),
                OrderStatus.NO_CARRINHO, Boolean.FALSE
        );

        log.info("Salvando pedido");
        return orderRepositoryPortOut.saveOrder(pedidoDto);
    }
}
