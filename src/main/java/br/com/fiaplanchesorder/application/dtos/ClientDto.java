package br.com.fiaplanchesorder.application.dtos;

public record ClientDto(
        String id,
        String cpf,
        String nome
) {

    public static ClientDto toClientResponseDto(ClientDto clienteDto) {
        return new ClientDto(
                clienteDto.id(),
                clienteDto.cpf(),
                clienteDto.nome()
        );
    }
}
