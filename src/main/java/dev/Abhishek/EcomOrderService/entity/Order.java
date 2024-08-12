package dev.Abhishek.EcomOrderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Order extends BaseModel{
    private UUID id;
    private UUID userId;
    @ManyToMany
    private List<OrderItem> items;
    private double totalPrice;
    private OrderStatus status;

}
