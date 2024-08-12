package dev.Abhishek.EcomOrderService.repository;

import dev.Abhishek.EcomOrderService.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
