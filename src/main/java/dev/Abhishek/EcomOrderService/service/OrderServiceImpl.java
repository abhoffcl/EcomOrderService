package dev.Abhishek.EcomOrderService.service;

import dev.Abhishek.EcomOrderService.dto.OrderItemDto;
import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;
import dev.Abhishek.EcomOrderService.entity.Order;
import dev.Abhishek.EcomOrderService.entity.OrderItem;
import dev.Abhishek.EcomOrderService.entity.OrderStatus;
import dev.Abhishek.EcomOrderService.repository.OrderRepository;
import dev.Abhishek.EcomOrderService.service.payment.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private PaymentService paymentService;
    private OrderRepository orderRepository;

    public OrderServiceImpl(PaymentService paymentService, OrderRepository orderRepository) {
        this.paymentService = paymentService;
        this.orderRepository = orderRepository;
    }

    @Override
    public boolean placeOrder(PlaceOrderRequestDto placeOrderRequestDto) {
        Order order = new Order();
        List<OrderItem> orderItemList = placeOrderRequestDto.getItems().stream()
                .map(dto -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setPrice(dto.getPrice());
                    orderItem.setQuantity(dto.getQuantity());
                    orderItem.setProductId(UUID.fromString(dto.getProductId()));
                    return orderItem;
                })
                .collect(Collectors.toList());
        double totalPrice = placeOrderRequestDto.getItems().stream()
                .mapToDouble(OrderItemDto::getPrice)
                .sum();
        order.setItems(orderItemList);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.PENDING);
        order.setUserId(UUID.fromString(placeOrderRequestDto.getUserId()));
        Order savedOrder = orderRepository.save(order);

        handlePaymentAsync(savedOrder, placeOrderRequestDto);
        return true;
    }

    private void handlePaymentAsync(Order savedOrder, PlaceOrderRequestDto placeOrderRequestDto) {
        CompletableFuture<String> paymentLinkFuture = CompletableFuture.supplyAsync(() ->
                paymentService.makePayment(savedOrder.getId(), savedOrder.getTotalPrice(), placeOrderRequestDto)
        );
        paymentLinkFuture.thenAccept(paymentLink -> {
            System.out.println("Payment link: " + paymentLink);
        });
        paymentLinkFuture.exceptionally(ex -> {
            System.out.println("Failed to generate payment link: " + ex.getMessage());
            return null;  // returning null or a fallback value to complete the future
        });
    }
}
