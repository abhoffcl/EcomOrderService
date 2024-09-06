package dev.Abhishek.EcomOrderService.service;

import dev.Abhishek.EcomOrderService.dto.PlaceOrderRequestDto;


public interface OrderService {
    boolean placeOrder(PlaceOrderRequestDto placeOrderRequestDto);

}
