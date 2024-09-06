package dev.Abhishek.EcomOrderService.controller.paymentClient;

import dev.Abhishek.EcomOrderService.dto.paymentClientDto.OrderStatusUpdateRequestDto;
import dev.Abhishek.EcomOrderService.service.payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderNotify")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentNotificationService) {
        this.paymentService = paymentNotificationService;
    }

    @PostMapping("/update-status")
    public ResponseEntity<Void>handleOrderStatusChange(@RequestBody OrderStatusUpdateRequestDto orderStatusUpdateRequestDto){
        paymentService.handleOrderStatusChange(orderStatusUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

}
