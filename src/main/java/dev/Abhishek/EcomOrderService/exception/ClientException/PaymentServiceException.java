package dev.Abhishek.EcomOrderService.exception.ClientException;

public class PaymentServiceException extends RuntimeException{
    public PaymentServiceException(String message) {
        super(message);
    }
}
