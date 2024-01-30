package br.com.fiaplanchesorder.infra.exception;

import br.com.fiaplanchesorder.infra.exception.handler.BadRequestException;
import br.com.fiaplanchesorder.infra.exception.handler.MessageDefaultExceptionHandler;
import br.com.fiaplanchesorder.infra.exception.handler.OrderBusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientException;

import java.util.Date;

@ControllerAdvice(basePackages = "br.com.fiaplanchesorder.infra.rest")
@Slf4j
public class ExceptionController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerNotFoundException(EntityNotFoundException exception){
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(WebClientException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerNotFoundException(WebClientException exception){
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerNotFoundException(RuntimeException exception){
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerBadRequestException(BadRequestException exception) {
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OrderBusinessException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerBadRequestException(OrderBusinessException exception) {
        log.error(exception.getMessage(), exception);
        return new ResponseEntity<>(
                new MessageDefaultExceptionHandler(
                        exception.getMessage(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDefaultExceptionHandler> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        var errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.badRequest().body(
                new MessageDefaultExceptionHandler(
                        errors,
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        new Date()
                )
        );
    }
}
