package dev.Abhishek.EcomOrderService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMessageDto {
    private String name;
    private String email;
    private String from;
    private String body;
    private String subject;
}
