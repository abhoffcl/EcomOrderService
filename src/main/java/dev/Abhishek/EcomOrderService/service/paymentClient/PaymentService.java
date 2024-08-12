package dev.Abhishek.EcomOrderService.service.paymentClient;

import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;
import dev.Abhishek.EcomOrderService.dto.paymentClientDto.OrderStatusUpdateRequestDto;
import dev.Abhishek.EcomOrderService.dto.paymentClientDto.PaymentRequestDto;

import java.util.UUID;

public interface PaymentService {
    void handleOrderStatusChange(OrderStatusUpdateRequestDto orderStatusUpdateRequestDto);
    String makePayment(UUID orderId, double totalPrice, PlaceOrderRequestDto placeOrderRequestDto);
}
