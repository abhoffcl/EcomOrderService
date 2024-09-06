package dev.Abhishek.EcomOrderService.exception;

public class KafkaMessagingException extends RuntimeException{
    public KafkaMessagingException(String message) {
        super(message);
    }
}
