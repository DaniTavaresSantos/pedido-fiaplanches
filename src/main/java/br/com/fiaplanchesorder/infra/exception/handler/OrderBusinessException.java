package br.com.fiaplanchesorder.infra.exception.handler;

public class OrderBusinessException extends RuntimeException{

        public OrderBusinessException(String message) {
            super(message);
        }
}
