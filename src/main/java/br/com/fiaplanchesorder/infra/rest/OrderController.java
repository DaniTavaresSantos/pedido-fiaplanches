package br.com.fiaplanchesorder.infra.rest;

import br.com.fiaplanchesorder.application.dtos.*;
import br.com.fiaplanchesorder.application.usecases.*;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/order")
public class OrderController {

    private final FindOrderClientUseCase findOrderClientUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final PaymentOrderUseCase paymentOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final FindOrderOrderedUseCase findOrderOrderedUseCase;

    public OrderController(FindOrderClientUseCase findOrderClientUseCase,
                           FindOrderUseCase findOrderUseCase,
                           PaymentOrderUseCase paymentOrderUseCase,
                           CreateOrderUseCase createOrderUseCase,
                           UpdateOrderUseCase updateOrderUseCase,
                           FindOrderOrderedUseCase findOrderOrderedUseCase){
        this.findOrderClientUseCase = findOrderClientUseCase;
        this.findOrderUseCase = findOrderUseCase;
        this.paymentOrderUseCase = paymentOrderUseCase;
        this.createOrderUseCase = createOrderUseCase;
        this.updateOrderUseCase = updateOrderUseCase;
        this.findOrderOrderedUseCase = findOrderOrderedUseCase;
    }

    @GetMapping()
    public ResponseEntity<Page<OrderDto>> findOrders(@PageableDefault Pageable pageable) {
        PageInfoDto pageInfo = new PageInfoDto();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<OrderDto> orderDtos = findOrderUseCase.findOrders(pageInfo);

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<OrderDto>(orderDtos, pageable, orderDtos.size())
        );
    }

    @GetMapping("/find/{cpf}")
    public ResponseEntity<Page<OrderDto>> findOrderClient(@PageableDefault Pageable pageable, @PathVariable String cpf) {
        PageInfoDto pageInfo = new PageInfoDto();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<OrderDto> orderDtos = findOrderClientUseCase.findOrderClient(cpf, pageInfo);
        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<OrderDto>(orderDtos, pageable, orderDtos.size())
        );
    }

    @PostMapping("/pay-order")
    public ResponseEntity<OrderDto> pagaPedido(@RequestBody @Valid PaymentOrderDto paymentOrderDto) {
        OrderDto orderDto = paymentOrderUseCase.payOrder(paymentOrderDto);
        return ResponseEntity.ok().body(orderDto);
    }

    @PostMapping("/create-order")
    public ResponseEntity<Long> createOrder(@RequestBody @Valid CreateOrderDto createOrderDto) {
        OrderDto orderDto = createOrderUseCase.createOrder(createOrderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto.id());
    }

    @PutMapping("/update-order")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody @Valid UpdateOrderDto updateOrderDto) {
        OrderDto orderDto = updateOrderUseCase.updateOrder(updateOrderDto);
        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/ordered")
    public ResponseEntity<Page<OrderDto>> findOrderOrdered(@PageableDefault Pageable pageable) {
        List<OrderDto> orderOrdered = findOrderOrderedUseCase.findOrderOrdered();

        return ResponseEntity.status(HttpStatus.OK).body(
                new PageImpl<OrderDto>(orderOrdered, pageable, orderOrdered.size())
        );
    }
}
