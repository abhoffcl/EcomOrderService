package dev.Abhishek.EcomOrderService.service.kafkaProducer;


import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;
import dev.Abhishek.EcomOrderService.dto.paymentClientDto.OrderStatusUpdateRequestDto;

public interface KafkaMessagingService {
    public void send(OrderStatusUpdateRequestDto orderStatusUpdateRequestDto);
}
