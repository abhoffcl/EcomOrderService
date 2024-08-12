package dev.Abhishek.EcomOrderService.controller.paymentClient;

import dev.Abhishek.EcomOrderService.dto.paymentClientDto.OrderStatusUpdateRequestDto;
import dev.Abhishek.EcomOrderService.service.paymentClient.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderNotify")
public class PaymentNotificationController {
    private PaymentService paymentNotificationService;

    public PaymentNotificationController(PaymentService paymentNotificationService) {
        this.paymentNotificationService = paymentNotificationService;
    }

    @PostMapping("/update-status")
    public ResponseEntity<Void>handleOrderStatusChange(@RequestBody OrderStatusUpdateRequestDto orderStatusUpdateRequestDto){
        paymentNotificationService.handleOrderStatusChange(orderStatusUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

}
