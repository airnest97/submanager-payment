package com.interswitch.submanagerpayment.service;

import com.interswitch.submanagerpayment.dtos.requests.MobileDataCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.MobileDataWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.responses.SubscriptionPaymentResponse;

public interface DataTransactionService {
    SubscriptionPaymentResponse payForMobileDataWithWallet(MobileDataWalletPaymentRequest mobileDataWalletPaymentRequest);
    SubscriptionPaymentResponse payForMobileDataWithCard(MobileDataCardPaymentRequest mobileDataCardPaymentRequest);
}
