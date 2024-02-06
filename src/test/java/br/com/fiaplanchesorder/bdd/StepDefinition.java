package br.com.fiaplanchesorder.bdd;

import br.com.fiaplanchesorder.application.dtos.CreateOrderDto;
import br.com.fiaplanchesorder.application.dtos.OrderDto;
import br.com.fiaplanchesorder.application.dtos.PaymentOrderDto;
import br.com.fiaplanchesorder.domain.enums.OrderStatus;
import br.com.fiaplanchesorder.infra.repository.PostGresOrderRepository;
import br.com.fiaplanchesorder.infra.repository.entity.OrderEntity;
import br.com.fiaplanchesorder.infra.repository.entity.OrderProduct;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
public class StepDefinition {

    @Autowired
    private PostGresOrderRepository orderRepository;

    private Response response;

    private PaymentOrderDto paymentOrderDto;
    private CreateOrderDto createOrderDto;

    private final String END_API_ORDER = "http://localhost:8081/v1/order";

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:" + 8081;
        orderRepository.deleteAll();

        System.out.println("Including Data.....");

        var orderProductList = new ArrayList<OrderProduct>();
        orderProductList.add(new OrderProduct(1L, 1L));
        orderProductList.add(new OrderProduct(2L, 2L));
        orderProductList.add(new OrderProduct(3L, 3L));
        orderProductList.add(new OrderProduct(4L, 4L));
        orderProductList.add(new OrderProduct(5L, 5L));
        orderProductList.add(new OrderProduct(6L, 6L));

        OrderEntity c1 = new OrderEntity(1L,
                "38037984850", orderProductList.subList(0, 1), LocalDateTime.now(), OrderStatus.EM_PREPARO, true);
        OrderEntity c2 = new OrderEntity(1L,
                "38037985822", orderProductList.subList(2, 3), LocalDateTime.now(), OrderStatus.NO_CARRINHO, false);
        OrderEntity c3 = new OrderEntity(1L,
                "49174699881", orderProductList.subList(4, 5), LocalDateTime.now(), OrderStatus.PRONTO, true);
        OrderEntity c4 = new OrderEntity(1L,
                "10640145850", orderProductList.subList(6, 7), LocalDateTime.now(), OrderStatus.FINALIZADO, true);

        orderRepository.saveAll(List.of(c1, c2, c3, c4));

        System.out.println("data Included.....");

        createOrderDto = new CreateOrderDto("", new ArrayList<>());
    }

//    @Test
//    @Dado("o cliente de CPF {word} e nome {string}")
//    public void oClienteDeCPFENome(String cpf, String name) {
//        orderDto = new ClientRequestDto(cpf, name);
//    }
//
//    @Test
//    @Quando("for realizada a chamada no endpoint de criação de cliente")
//    public void for_realizada_a_chamada_no_endpoint_de_criação_de_cliente() {
//        response = given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(orderDto)
//                .when()
//                .post(END_API_CLIENTE + "/create");
//
//        response.then()
//                .statusCode(HttpStatus.CREATED.value())
//                .body(matchesJsonSchemaInClasspath("schemas/ClientSchema.json"));
//
//    }
//
//    @Test
//    @Quando("for realizada a chamada no endpoint de busca de cliente")
//    public void forRealizadaAChamadaNoEndpointDeBuscaDeCliente() {
//        response = given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .when()
//                .get(END_API_CLIENTE + "/find/" + orderDto.cpf());
//
//        response.then()
//                .statusCode(HttpStatus.OK.value())
//                .body(matchesJsonSchemaInClasspath("schemas/ClientSchema.json"));
//    }

//    @Test
//    @Quando("for realizada a chamada no endpoint de atualizacao de cliente")
//    public void forRealizadaAChamadaNoEndpointDeAtualizacaoDeCliente() {
//        orderDto = new ClientRequestDto(orderDto.cpf(), nomeAtual);
//
//        response = given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .body(orderDto)
//                .when()
//                .put(END_API_CLIENTE + "/update");
//
//        response.then()
//                .statusCode(HttpStatus.OK.value())
//                .body(matchesJsonSchemaInClasspath("schemas/ClientSchema.json"));
//
//        response.then().assertThat().body("nome", Is.is(nomeAtual));
//    }

//    @Test
//    @Quando("for realizada a chamada no endpoint de delecao de cliente")
//    public void for_realizada_a_chamada_no_endpoint_de_delecao_de_cliente() {
//
//        response = given()
//                .when()
//                .delete(END_API_CLIENTE + "/delete/" + orderDto.cpf());
//
//        response.then().assertThat().statusCode(HttpStatus.OK.value());
//        System.out.println(response.body().prettyPrint());
//
//    }
//
//    @Test
//    @Entao("o cliente deve ser atualizado com sucesso na base")
//    public void oClienteDeveSerAtualizadoComSucessoNaBase() {
//
//        ClientEntity clienteEntity = orderRepository.findByCpf(orderDto.cpf()).
//                orElseThrow();
//
//        assertEquals(nomeAtual, clienteEntity.toClienteDto().nome());
//    }
//    @E("alterar os dados para CPF {word} e nome {word}")
//    public void alterarOsDadosParaCPFENomeCleiton(String cpf, String name) {
//        nomeAtual = name;
//    }
//
//
//    @Test
//    @Entao("o cliente não deve ser encontrado na base")
//    public void oClienteNaoDeveSerEncontradoNaBase() {
//
//        response = given()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .when()
//                .get(END_API_CLIENTE + "/find/" + orderDto.cpf());
//
//        response.then().assertThat().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//    }

    @Dado("o cliente de CPF {word} e pedidos {int}, {int}, {int}")
    public void oClienteDeCPFEPedidos(String cpf, int pedido1, int pedido2, int pedido3) {
        createOrderDto = new CreateOrderDto(cpf, List.of((long) pedido1, (long) pedido2, (long) pedido3));
    }

    @Quando("for realizada a chamada no endpoint de criação de pedido")
    public void forRealizadaAChamadaNoEndpointDeCriaçãoDePedido() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createOrderDto)
                .when()
                .post(END_API_ORDER + "/create-order");

        response.then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Entao("o cliente deve ser localizado com sucesso na base")
    public void oClienteDeveSerLocalizadoComSucessoNaBase() {
        Pageable pageable = PageRequest.of(1, 1);
        List<OrderDto> orderEntity = orderRepository.findAllByCpf(createOrderDto.cpf(), pageable ).map(OrderEntity::toOrderDto).toList();

        Assertions.assertEquals(orderEntity.stream().findFirst().get().cpf(), createOrderDto.cpf());
    }
}
