package dev.Abhishek.EcomOrderService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "ECOM_ORDER")
public class Order extends BaseModel{
    private UUID userId;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}
