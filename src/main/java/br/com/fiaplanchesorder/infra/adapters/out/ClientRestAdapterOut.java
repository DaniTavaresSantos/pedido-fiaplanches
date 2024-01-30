package br.com.fiaplanchesorder.infra.adapters.out;

import br.com.fiaplanchesorder.application.dtos.ClientDto;
import br.com.fiaplanchesorder.application.ports.out.ClientRestPortOut;
import br.com.fiaplanchesorder.infra.exception.handler.MessageDefaultExceptionHandler;
import br.com.fiaplanchesorder.infra.exception.handler.OrderBusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
public class ClientRestAdapterOut implements ClientRestPortOut {

    @Autowired
    private WebClient restClient;

    @Override
    public Optional<ClientDto> findCpf(String cpf) {

        ClientDto monoClientDto = restClient
                .get()
                .uri("/find/{cpf}", cpf)
                .retrieve()
                .onStatus(
                        httpStatus -> !httpStatus.is2xxSuccessful(),
                        clientResponse -> clientResponse.bodyToMono(MessageDefaultExceptionHandler.class).
                                map(
                                        messageDefaultExceptionHandler -> new OrderBusinessException(messageDefaultExceptionHandler.getException().stream().map(String::valueOf).toList().toString())
                                )
                )
                .bodyToMono(ClientDto.class)
                .block();

        return Optional.of(monoClientDto);
    }
}
