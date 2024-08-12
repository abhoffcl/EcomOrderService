package dev.Abhishek.EcomOrderService.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class OrderItem extends BaseModel{
    private UUID productId;
    private int quantity;
    private double price;
}
