package dev.Abhishek.EcomOrderService.dto.productClientDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FailedOrderProductsDto {
    private String productId;
    private Integer quantity;
}
