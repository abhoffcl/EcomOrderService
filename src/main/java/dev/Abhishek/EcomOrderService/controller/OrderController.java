package dev.Abhishek.EcomOrderService.controller;

import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;
import dev.Abhishek.EcomOrderService.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/placeOrder")
    public ResponseEntity<Boolean>createOrder(PlaceOrderRequestDto placeOrderRequestDto){
        return ResponseEntity.ok(orderService.placeOrder(placeOrderRequestDto));
    }

}
