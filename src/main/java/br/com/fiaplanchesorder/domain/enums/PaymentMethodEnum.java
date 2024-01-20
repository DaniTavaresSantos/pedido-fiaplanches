package br.com.fiaplanchesorder.domain.enums;

public enum PaymentMethodEnum {

    CREDIT_CARD("Cartão de Crédito"),
    DEBIT_CARD("Cartão de Débito"),
    PIX("PIX"),
    QR_CODE("QR code");


    PaymentMethodEnum(String paymentMethod) {
    }
}
