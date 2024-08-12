package dev.Abhishek.EcomOrderService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemDto {
    private String productId;
    private int quantity;
    private double price;
}