package dev.Abhishek.EcomOrderService.client;

import dev.Abhishek.EcomOrderService.dto.paymentClientDto.PaymentRequestDto;
import dev.Abhishek.EcomOrderService.exception.ClientException.PaymentServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
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
        try {
            ResponseEntity<String> payment =
                    restTemplate.postForEntity(makePaymentUrl,paymentRequestDto,String.class);
            return payment.getBody();
        } catch (ResourceAccessException e) {
            throw new PaymentServiceException("Failed to initiate payment: Timeout or resource access issue.");
        } catch (RestClientException e) {
            throw new PaymentServiceException("Failed to initiate payment: An error occurred during REST call.");
        }
    }

}
