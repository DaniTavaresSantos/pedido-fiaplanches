package br.com.fiaplanchesorder.application.ports.out;

import br.com.fiaplanchesorder.application.dtos.ClientDto;

import java.util.Optional;

public interface ClientRestPortOut {

    Optional<ClientDto> findCpf(String cpf);
}
