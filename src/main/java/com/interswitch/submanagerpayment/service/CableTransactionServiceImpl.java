package com.interswitch.submanagerpayment.service;

import com.interswitch.submanagerpayment.data.enums.PaymentType;
import com.interswitch.submanagerpayment.data.models.CableTransaction;
import com.interswitch.submanagerpayment.data.repositories.CableTransactionRepository;
import com.interswitch.submanagerpayment.dtos.requests.DstvCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.DstvWalletPaymentRequest;

import com.interswitch.submanagerpayment.dtos.requests.GotvCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.GotvWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.responses.SubscriptionPaymentResponse;
import com.interswitch.submanagerpayment.exceptions.SubmanagerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class CableTransactionServiceImpl implements CableTransactionService{
    private final CableTransactionRepository cableTransactionRepository;
    @Override
    public SubscriptionPaymentResponse payForDstvWithCard(DstvCardPaymentRequest dstvCardPaymentRequest) {
        if (isValidCreditCardNumber(dstvCardPaymentRequest.getCardNumber())) {
            if (dstvCardPaymentRequest.getCardExpiryDate().isAfter(LocalDate.now().plusDays(1))) {
                CableTransaction cableTransaction = buildCableTransaction(dstvCardPaymentRequest);
                cableTransactionRepository.save(cableTransaction);
                return buildDstvCardPaymentResponse(dstvCardPaymentRequest);
            }
            else {
                throw new SubmanagerException("The card provided has expired. Please check again...", 400);
            }
        }
        throw new SubmanagerException("The card provided is not valid. Please check again...", 400);
    }

    @Override
    public SubscriptionPaymentResponse payForDstvWithWallet(DstvWalletPaymentRequest dstvWalletPaymentRequest) {
        CableTransaction cableTransaction = CableTransaction.builder()
                .createdBy(dstvWalletPaymentRequest.getFullName())
                .dateAdded(LocalDate.now())
                .nextPayment(LocalDate.now().plusDays(30))
                .phoneNumber(dstvWalletPaymentRequest.getPhoneNumber())
                .paymentType(PaymentType.WALLET)
                .priceOfSubscription(dstvWalletPaymentRequest.getPriceOfSubscription())
                .build();
        cableTransactionRepository.save(cableTransaction);
        return buildDstvWalletPaymentResponse(dstvWalletPaymentRequest);
    }

    @Override
    public SubscriptionPaymentResponse payForGotvWithCard(GotvCardPaymentRequest gotvCardPaymentRequest) {
        if (isValidCreditCardNumber(gotvCardPaymentRequest.getCardNumber())) {
            if (gotvCardPaymentRequest.getCardExpiryDate().isAfter(LocalDate.now().plusDays(1))) {
                CableTransaction cableTransaction = buildGoTvCableTransaction(gotvCardPaymentRequest);
                cableTransactionRepository.save(cableTransaction);
                return buildGotvCardPaymentResponse(gotvCardPaymentRequest);
            }
            else {
                throw new SubmanagerException("The card provided has expired. Please check again...", 400);
            }
        }
        throw new SubmanagerException("The card provided is not valid. Please check again...", 400);
    }

    private SubscriptionPaymentResponse buildGotvCardPaymentResponse(GotvCardPaymentRequest gotvCardPaymentRequest) {
        return SubscriptionPaymentResponse.builder()
                .dateOfPayment(LocalDate.now())
                .nextPaymentDate(LocalDate.now().plusDays(30))
                .priceOfSubscription(gotvCardPaymentRequest.getPriceOfSubscription())
                .subscriptionId(Long.valueOf(gotvCardPaymentRequest.getSubscriptionId()))
                .successful(true)
                .build();
    }

    private CableTransaction buildGoTvCableTransaction(GotvCardPaymentRequest gotvCardPaymentRequest) {
        return CableTransaction.builder()
                .createdBy(gotvCardPaymentRequest.getFullName())
                .dateAdded(LocalDate.now())
                .nextPayment(LocalDate.now().plusDays(30))
                .phoneNumber(gotvCardPaymentRequest.getPhoneNumber())
                .paymentType(PaymentType.CARD)
                .smartCardNumber(gotvCardPaymentRequest.getGoTv_IUC_Number())
                .priceOfSubscription(gotvCardPaymentRequest.getPriceOfSubscription())
                .build();
    }

    @Override
    public SubscriptionPaymentResponse payForGotvWithWallet(GotvWalletPaymentRequest gotvWalletPaymentRequest) {
        CableTransaction cableTransaction = CableTransaction.builder()
                .createdBy(gotvWalletPaymentRequest.getFullName())
                .dateAdded(LocalDate.now())
                .nextPayment(LocalDate.now().plusDays(30))
                .phoneNumber(gotvWalletPaymentRequest.getPhoneNumber())
                .paymentType(PaymentType.WALLET)
                .priceOfSubscription(gotvWalletPaymentRequest.getPriceOfSubscription())
                .build();
        cableTransactionRepository.save(cableTransaction);
        return buildGotvWalletPaymentResponse(gotvWalletPaymentRequest);
    }

    private SubscriptionPaymentResponse buildGotvWalletPaymentResponse(GotvWalletPaymentRequest gotvWalletPaymentRequest) {
        return SubscriptionPaymentResponse.builder()
                .dateOfPayment(LocalDate.now())
                .nextPaymentDate(LocalDate.now().plusDays(30))
                .priceOfSubscription(gotvWalletPaymentRequest.getPriceOfSubscription())
                .subscriptionId(Long.valueOf(gotvWalletPaymentRequest.getSubscriptionId()))
                .successful(true)
                .build();
    }

    private SubscriptionPaymentResponse buildDstvWalletPaymentResponse(DstvWalletPaymentRequest dstvWalletPaymentRequest) {
        return SubscriptionPaymentResponse.builder()
                .dateOfPayment(LocalDate.now())
                .nextPaymentDate(LocalDate.now().plusDays(30))
                .priceOfSubscription(dstvWalletPaymentRequest.getPriceOfSubscription())
                .subscriptionId(Long.valueOf(dstvWalletPaymentRequest.getSubscriptionId()))
                .successful(true)
                .build();
    }

    private CableTransaction buildCableTransaction(DstvCardPaymentRequest dstvCardPaymentRequest) {
        return CableTransaction.builder()
                .createdBy(dstvCardPaymentRequest.getFullName())
                .dateAdded(LocalDate.now())
                .nextPayment(LocalDate.now().plusDays(30))
                .phoneNumber(dstvCardPaymentRequest.getPhoneNumber())
                .paymentType(PaymentType.CARD)
                .smartCardNumber(dstvCardPaymentRequest.getDstvSmartCardNumber())
                .priceOfSubscription(dstvCardPaymentRequest.getPriceOfSubscription())
                .build();
    }

    private SubscriptionPaymentResponse buildDstvCardPaymentResponse(DstvCardPaymentRequest dstvCardPaymentRequest) {
        return SubscriptionPaymentResponse.builder()
                .dateOfPayment(LocalDate.now())
                .nextPaymentDate(LocalDate.now().plusDays(30))
                .priceOfSubscription(dstvCardPaymentRequest.getPriceOfSubscription())
                .subscriptionId(Long.valueOf(dstvCardPaymentRequest.getSubscriptionId()))
                .successful(true)
                .build();
    }

    public static boolean isValidCreditCardNumber(String creditCardNumber) {
        return LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(creditCardNumber);
    }
}
