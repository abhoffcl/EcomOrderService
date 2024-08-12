package dev.Abhishek.EcomOrderService.client;

import dev.Abhishek.EcomOrderService.dto.paymentClientDto.PaymentRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PaymentClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String paymentServiceBaseUrl;
    private String paymentServiceMakePaymentPath;

    public PaymentClient(RestTemplateBuilder restTemplateBuilder, @Value("${paymentService.api.base.url}") String paymentServiceBaseUrl,@Value("${paymentService.api.makePayment.path}") String paymentServiceMakePaymentPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.paymentServiceBaseUrl = paymentServiceBaseUrl;
        this.paymentServiceMakePaymentPath = paymentServiceMakePaymentPath;
    }

    public String  makePayment(PaymentRequestDto paymentRequestDto){
        String makePaymentUrl = paymentServiceBaseUrl.concat(paymentServiceMakePaymentPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<String> payment =
                restTemplate.postForEntity(makePaymentUrl,paymentRequestDto,String.class);
        return payment.getBody();
    }
}
