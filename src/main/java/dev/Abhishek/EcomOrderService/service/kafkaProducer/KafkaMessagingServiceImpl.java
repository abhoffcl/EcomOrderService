package dev.Abhishek.EcomOrderService.service.kafkaProducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.Abhishek.EcomOrderService.config.KafkaProducer;
import dev.Abhishek.EcomOrderService.dto.OrderMessageDto;
import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;
import dev.Abhishek.EcomOrderService.dto.paymentClientDto.OrderStatusUpdateRequestDto;
import dev.Abhishek.EcomOrderService.exception.KafkaMessagingException;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessagingServiceImpl implements KafkaMessagingService {
    private KafkaProducer kafkaProducer;
    private ObjectMapper objectMapper;

    public KafkaMessagingServiceImpl(KafkaProducer kafkaProducer, ObjectMapper objectMapper) {
        this.kafkaProducer = kafkaProducer;
        this.objectMapper = objectMapper;
    }

    public void send(OrderStatusUpdateRequestDto orderStatusUpdateRequestDto)  {
        OrderMessageDto orderMessageDto = new OrderMessageDto();
        orderMessageDto.setName(orderStatusUpdateRequestDto.getUserName());
        orderMessageDto.setEmail(orderStatusUpdateRequestDto.getUserEmail());
        orderMessageDto.setFrom("admin@ecom.com");
        orderMessageDto.setBody("Hi "+ orderStatusUpdateRequestDto.getUserName()+", thanks for shopping with us! We've received your order." +
                " You'll receive a confirmation email with tracking information shortly.");
    try{
        String message = objectMapper.writeValueAsString(orderMessageDto);
        kafkaProducer.send("sendEmail", message);
        } catch (JsonProcessingException e) {
            throw new KafkaMessagingException("Failed to process JSON for Kafka message");
        } catch (RuntimeException e) {
            throw new KafkaMessagingException("Failed to send Kafka message");
        }
    }
}
