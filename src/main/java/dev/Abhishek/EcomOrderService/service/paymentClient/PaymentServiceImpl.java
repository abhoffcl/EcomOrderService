package dev.Abhishek.EcomOrderService.service.paymentClient;

import dev.Abhishek.EcomOrderService.client.PaymentClient;
import dev.Abhishek.EcomOrderService.client.ProductClient;
import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;
import dev.Abhishek.EcomOrderService.dto.paymentClientDto.OrderStatusUpdateRequestDto;
import dev.Abhishek.EcomOrderService.dto.paymentClientDto.PaymentRequestDto;
import dev.Abhishek.EcomOrderService.dto.productClientDto.FailedOrderProductsDto;
import dev.Abhishek.EcomOrderService.entity.Order;
import dev.Abhishek.EcomOrderService.entity.OrderStatus;
import dev.Abhishek.EcomOrderService.exception.OrderNotFoundException;
import dev.Abhishek.EcomOrderService.repository.OrderRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PaymentServiceImpl implements PaymentService {
    private OrderRepository orderRepository;
    private ProductClient productClient;
    private PaymentClient paymentClient;

    public PaymentServiceImpl(OrderRepository orderRepository, ProductClient productClient, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
        this.paymentClient = paymentClient;
    }

    @Override
    public void handleOrderStatusChange(OrderStatusUpdateRequestDto orderStatusUpdateRequestDto) {
        // update order status
        // if failed .make a call to product service via Product service client.
        UUID orderId =UUID.fromString(orderStatusUpdateRequestDto.getOrderId());
        Order savedOrder =orderRepository.findById(orderId).
                orElseThrow(()->new OrderNotFoundException("Order not found for id "+orderId));
        if(orderStatusUpdateRequestDto.getStatus().equals(OrderStatus.FAILED)){
            savedOrder.setStatus(OrderStatus.FAILED);
            List<FailedOrderProductsDto> failedOrderProductsInfoDtos = savedOrder.getItems()
                    .stream()
                    .map(orderItem -> {
                        FailedOrderProductsDto dto = new FailedOrderProductsDto();
                        dto.setProductId(orderItem.getProductId().toString());
                        dto.setQuantity(orderItem.getQuantity());
                        return dto;
                    })
                    .collect(Collectors.toList());
            productClient.notifyOrderFailure(failedOrderProductsInfoDtos);
        }
        else
            savedOrder.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(savedOrder);

    }
    @Override
    public String makePayment(UUID orderId, double totalPrice, PlaceOrderRequestDto placeOrderRequestDto) {
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
        paymentRequestDto.setUserId(placeOrderRequestDto.getUserId());
        paymentRequestDto.setOrderId(orderId.toString());
        paymentRequestDto.setAmount(totalPrice);
        paymentRequestDto.setCustomerEmail(placeOrderRequestDto.getEmail());
        paymentRequestDto.setCustomerName(placeOrderRequestDto.getUserName());
        paymentRequestDto.setCustomerPhone(placeOrderRequestDto.getPhoneNumber());

        String paymentLink = paymentClient.makePayment(paymentRequestDto);
        return paymentLink;
    }
}
