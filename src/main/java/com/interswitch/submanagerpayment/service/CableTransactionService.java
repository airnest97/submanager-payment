package com.interswitch.submanagerpayment.service;

import com.interswitch.submanagerpayment.dtos.requests.DstvCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.DstvWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.GotvCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.GotvWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.responses.SubscriptionPaymentResponse;

public interface CableTransactionService {
    SubscriptionPaymentResponse payForDstvWithCard(DstvCardPaymentRequest dstvCardPaymentRequest);
    SubscriptionPaymentResponse payForDstvWithWallet(DstvWalletPaymentRequest dstvWalletPaymentRequest);
    SubscriptionPaymentResponse payForGotvWithCard(GotvCardPaymentRequest gotvCardPaymentRequest);
    SubscriptionPaymentResponse payForGotvWithWallet(GotvWalletPaymentRequest gotvWalletPaymentRequest);
}
