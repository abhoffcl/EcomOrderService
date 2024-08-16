package dev.Abhishek.EcomOrderService.client;

import dev.Abhishek.EcomOrderService.dto.productClientDto.FailedOrderProductsDto;
import dev.Abhishek.EcomOrderService.exception.ClientException.PaymentServiceException;
import dev.Abhishek.EcomOrderService.exception.ClientException.ProductServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ProductClient {
    private RestTemplateBuilder restTemplateBuilder;
    private String productServiceBaseUrl;
    private String productServiceOrderFailedNotificationPath;

    public ProductClient(RestTemplateBuilder restTemplateBuilder, @Value("${productService.api.base.url}") String productServiceBaseUrl, @Value("${productService.api.OrderFailureNotification.path}") String productServiceOrderFailedNotificationPath) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productServiceBaseUrl = productServiceBaseUrl;
        this.productServiceOrderFailedNotificationPath = productServiceOrderFailedNotificationPath;
    }
    public void notifyOrderFailure(List<FailedOrderProductsDto> failedProducts) {
        String url = productServiceBaseUrl.concat(productServiceOrderFailedNotificationPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(url, failedProducts, Void.class);
            // Handle response if necessary

        } catch (ResourceAccessException e) {
            throw new ProductServiceException("Failed to notify product service: Timeout or resource access issue.");
        } catch (RestClientException e) {
            throw new ProductServiceException("Failed to notify product service: An error occurred during REST call.");
        }
    }

}
