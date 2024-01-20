package br.com.fiaplanchesorder.application.dtos;

public record ClientDto(
        Long id,
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
