package com.java.services;

import java.util.Calendar;
import java.util.Date;

import com.java.domain.PaymentSlip;

import org.springframework.stereotype.Service;

@Service
public class PaymentSlipService {

    public void createBankBill(PaymentSlip payment, Date instant){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());
    }
    
}