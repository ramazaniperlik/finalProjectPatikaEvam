package com.finalproject.Service;

import com.finalproject.Entity.Payment;

import java.util.List;

public interface IPaymentService {
    List<Payment> getPayments();
    boolean payInvoice(int invoiceId);
}
