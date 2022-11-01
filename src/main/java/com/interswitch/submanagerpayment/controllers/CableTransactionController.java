package com.interswitch.submanagerpayment.controllers;


import com.interswitch.submanagerpayment.dtos.requests.DstvCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.DstvWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.GotvCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.GotvWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.responses.PaymentApiResponse;
import com.interswitch.submanagerpayment.dtos.responses.SubscriptionPaymentResponse;
import com.interswitch.submanagerpayment.service.CableTransactionService;
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
public class CableTransactionController {
    private final CableTransactionService cableTransactionService;

    @PostMapping(value = "/cableTransaction/dstvCard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> payForDstvWithCard(@RequestBody DstvCardPaymentRequest dstvCardPaymentRequest) {
        SubscriptionPaymentResponse subscriptionPaymentResponse = cableTransactionService.payForDstvWithCard(dstvCardPaymentRequest);
        PaymentApiResponse apiResponse = PaymentApiResponse.builder()
                .subscriptionPaymentResponse(subscriptionPaymentResponse)
                .message("successful")
                .status("success")
                .successful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/cableTransaction/dstvWallet", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> payForDstvWithWallet(@RequestBody DstvWalletPaymentRequest dstvWalletPaymentRequest) {
        SubscriptionPaymentResponse subscriptionPaymentResponse = cableTransactionService.payForDstvWithWallet(dstvWalletPaymentRequest);
        PaymentApiResponse apiResponse = PaymentApiResponse.builder()
                .subscriptionPaymentResponse(subscriptionPaymentResponse)
                .message("successful")
                .status("success")
                .successful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/cableTransaction/gotvWallet", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> payForGotvWithWallet(@RequestBody GotvWalletPaymentRequest gotvWalletPaymentRequest) {
        SubscriptionPaymentResponse subscriptionPaymentResponse = cableTransactionService.payForGotvWithWallet(gotvWalletPaymentRequest);
        PaymentApiResponse apiResponse = PaymentApiResponse.builder()
                .subscriptionPaymentResponse(subscriptionPaymentResponse)
                .message("successful")
                .status("success")
                .successful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/cableTransaction/gotvCard", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> payForGotvWithCard(@RequestBody GotvCardPaymentRequest gotvCardPaymentRequest) {
        SubscriptionPaymentResponse subscriptionPaymentResponse = cableTransactionService.payForGotvWithCard(gotvCardPaymentRequest);
        PaymentApiResponse apiResponse = PaymentApiResponse.builder()
                .subscriptionPaymentResponse(subscriptionPaymentResponse)
                .message("successful")
                .status("success")
                .successful(true)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
