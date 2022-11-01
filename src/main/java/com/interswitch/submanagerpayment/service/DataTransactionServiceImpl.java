package com.interswitch.submanagerpayment.service;

import com.interswitch.submanagerpayment.data.enums.DataPlan;
import com.interswitch.submanagerpayment.data.enums.PaymentType;
import com.interswitch.submanagerpayment.data.models.DataTransaction;
import com.interswitch.submanagerpayment.data.repositories.DataTransactionRepository;
import com.interswitch.submanagerpayment.dtos.requests.MobileDataCardPaymentRequest;
import com.interswitch.submanagerpayment.dtos.requests.MobileDataWalletPaymentRequest;
import com.interswitch.submanagerpayment.dtos.responses.SubscriptionPaymentResponse;
import com.interswitch.submanagerpayment.exceptions.SubmanagerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class DataTransactionServiceImpl implements DataTransactionService{
    private final DataTransactionRepository dataTransactionRepository;
    @Override
    public SubscriptionPaymentResponse payForMobileDataWithWallet(MobileDataWalletPaymentRequest mobileDataWalletPaymentRequest) {
        if(nigerianPhoneNumberIsValid(mobileDataWalletPaymentRequest.getPhoneNumber())){
            DataTransaction dataTransaction = DataTransaction.builder()
                    .createdBy(mobileDataWalletPaymentRequest.getFullName())
                    .dateAdded(LocalDate.now())
                    .nextPayment(LocalDate.now().plusDays(30))
                    .phoneNumber(mobileDataWalletPaymentRequest.getPhoneNumber())
                    .paymentType(PaymentType.WALLET)
                    .priceOfSubscription(mobileDataWalletPaymentRequest.getPriceOfSubscription())
                    .mobileNetwork(mobileDataWalletPaymentRequest.getMobileNetwork())
                    .dataPlan(generateDataPlan(mobileDataWalletPaymentRequest.getPriceOfSubscription()))
                    .build();
            dataTransactionRepository.save(dataTransaction);
            return buildMobileDataWalletPaymentResponse(mobileDataWalletPaymentRequest);
        }
        throw new SubmanagerException("Invalid phone number. Kindly check the phone number and try again!",400);
    }



    private SubscriptionPaymentResponse buildMobileDataWalletPaymentResponse(MobileDataWalletPaymentRequest mobileDataWalletPaymentRequest) {
        return SubscriptionPaymentResponse.builder()
                .dateOfPayment(LocalDate.now())
                .nextPaymentDate(LocalDate.now().plusDays(30))
                .priceOfSubscription(mobileDataWalletPaymentRequest.getPriceOfSubscription())
                .subscriptionId(Long.valueOf(mobileDataWalletPaymentRequest.getSubscriptionId()))
                .successful(true)
                .build();
    }

    private DataPlan generateDataPlan(BigDecimal priceOfSubscription) {
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(250)) == 0){
            return DataPlan.FIVE_HUNDRED_MB;
        }
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(500)) == 0){
            return DataPlan.ONE_GB;
        }
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(1000)) == 0){
            return DataPlan.TWO_GB;
        }
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(3000)) == 0){
            return DataPlan.FIVE_GB;
        }
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(5000)) == 0){
            return DataPlan.TEN_GB;
        }
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(7500)) == 0){
            return DataPlan.TWENTY_GB;
        }
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(10000)) == 0){
            return DataPlan.FIFTY_GB;
        }
        if(priceOfSubscription.compareTo(BigDecimal.valueOf(20000)) == 0){
            return DataPlan.HUNDRED_GB;
        }
        throw new SubmanagerException("Ops! Cannot find data plan for the amount selected. Please check and select again",400);
    }

    private boolean nigerianPhoneNumberIsValid(String phoneNumber) {
        String regex = "(^(\\+)?234[\\( ]?[0-9]{3}\\)? ?[0-9]{3}[\\- ]?[0-9]{4})|(^[0-9]{4}[\\- ]?[0-9]{3}[\\- ]?[0-9]{4})|(^01 ?[0-9]{3} ?[0-9]{4})|(^\\+234 1 [0-9]{3} [0-9]{4})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    @Override
    public SubscriptionPaymentResponse payForMobileDataWithCard(MobileDataCardPaymentRequest mobileDataCardPaymentRequest) {
        if(nigerianPhoneNumberIsValid(mobileDataCardPaymentRequest.getPhoneNumber())){
            DataTransaction dataTransaction = DataTransaction.builder()
                    .createdBy(mobileDataCardPaymentRequest.getFullName())
                    .dateAdded(LocalDate.now())
                    .nextPayment(LocalDate.now().plusDays(30))
                    .phoneNumber(mobileDataCardPaymentRequest.getPhoneNumber())
                    .paymentType(PaymentType.CARD)
                    .priceOfSubscription(mobileDataCardPaymentRequest.getPriceOfSubscription())
                    .mobileNetwork(mobileDataCardPaymentRequest.getMobileNetwork())
                    .dataPlan(generateDataPlan(mobileDataCardPaymentRequest.getPriceOfSubscription()))
                    .build();
            dataTransactionRepository.save(dataTransaction);
            return buildMobileDataCardPaymentResponse(mobileDataCardPaymentRequest);
        }
        throw new SubmanagerException("Invalid phone number. Kindly check the phone number and try again!",400);
    }

    private SubscriptionPaymentResponse buildMobileDataCardPaymentResponse(MobileDataCardPaymentRequest mobileDataCardPaymentRequest) {
        return SubscriptionPaymentResponse.builder()
                .dateOfPayment(LocalDate.now())
                .nextPaymentDate(LocalDate.now().plusDays(30))
                .priceOfSubscription(mobileDataCardPaymentRequest.getPriceOfSubscription())
                .subscriptionId(Long.valueOf(mobileDataCardPaymentRequest.getSubscriptionId()))
                .successful(true)
                .build();
    }
}
