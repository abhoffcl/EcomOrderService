package dev.Abhishek.EcomOrderService.exception;

import dev.Abhishek.EcomOrderService.dto.ExceptionResponseDto;
import dev.Abhishek.EcomOrderService.exception.ClientException.PaymentServiceException;
import dev.Abhishek.EcomOrderService.exception.ClientException.ProductServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class GlobalExceptionHandler {
    @ExceptionHandler({PaymentServiceException.class})
    public ResponseEntity handlePaymentServiceException(PaymentServiceException pe) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),
                503
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.SERVICE_UNAVAILABLE);

    }
    @ExceptionHandler({ProductServiceException.class})
    public ResponseEntity handleProductServiceException(ProductServiceException pe) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),
                503
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.SERVICE_UNAVAILABLE);

    }
    @ExceptionHandler({OrderNotFoundException.class})
    public ResponseEntity handleOrderNotFoundException(OrderNotFoundException pe) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(
                pe.getMessage(),
                404
        );
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.NOT_FOUND);

    }
}