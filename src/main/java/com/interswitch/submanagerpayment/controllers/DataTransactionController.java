package com.interswitch.submanagerpayment.controllers;

import com.interswitch.submanagerpayment.dtos.requests.MobileDataCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.MobileDataWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.responses.PaymentApiResponse;
import com.interswitch.submanagerpayment.dtos.responses.SubscriptionPaymentResponse;
import com.interswitch.submanagerpayment.service.DataTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class DataTransactionController {
    private final DataTransactionService dataTransactionService;

    @PostMapping(value = "/dataTransaction/wallet", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> payForMobileDataWithWallet(@RequestBody MobileDataWalletPaymentRequest mobileDataWalletPaymentRequest) {
        SubscriptionPaymentResponse subscriptionPaymentResponse = dataTransactionService.payForMobileDataWithWallet(mobileDataWalletPaymentRequest);
        PaymentApiResponse apiResponse = PaymentApiResponse.builder()
                .subscriptionPaymentResponse(subscriptionPaymentResponse)
                .message("successful")
                .status("success")
                .successful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/dataTransaction/card", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> payForMobileDataWithCard(@RequestBody MobileDataCardPaymentRequest mobileDataCardPaymentRequest) {
        SubscriptionPaymentResponse subscriptionPaymentResponse = dataTransactionService.payForMobileDataWithCard(mobileDataCardPaymentRequest);
        PaymentApiResponse apiResponse = PaymentApiResponse.builder()
                .subscriptionPaymentResponse(subscriptionPaymentResponse)
                .message("successful")
                .status("success")
                .successful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
