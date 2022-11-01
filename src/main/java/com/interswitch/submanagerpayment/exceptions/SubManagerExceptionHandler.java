package com.interswitch.submanagerpayment.exceptions;

import com.interswitch.submanagerpayment.dtos.responses.PaymentApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class SubManagerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(SubmanagerException.class)
    public ResponseEntity<PaymentApiResponse> handleSubManagerException(SubmanagerException submanagerException){
        PaymentApiResponse apiResponse = PaymentApiResponse.builder()
                .successful(false)
                .subscriptionPaymentResponse(null)
                .message(submanagerException.getMessage())
                .status("unsuccessful")
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
