package dev.Abhishek.EcomOrderService.service.payment;

import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;
import dev.Abhishek.EcomOrderService.dto.paymentClientDto.OrderStatusUpdateRequestDto;
import dev.Abhishek.EcomOrderService.exception.ClientException.PaymentServiceException;
import dev.Abhishek.EcomOrderService.exception.ClientException.ProductServiceException;
import dev.Abhishek.EcomOrderService.exception.OrderNotFoundException;

import java.util.UUID;

public interface PaymentService {
    void handleOrderStatusChange(OrderStatusUpdateRequestDto orderStatusUpdateRequestDto)throws OrderNotFoundException, ProductServiceException;
    String makePayment(UUID orderId, double totalPrice, PlaceOrderRequestDto placeOrderRequestDto)throws PaymentServiceException;
}
