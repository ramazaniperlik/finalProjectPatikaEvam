package com.finalproject.Repository;

import com.finalproject.Entity.Payment;

import java.util.List;

public interface IPaymentDao {
    List<Payment> getPayments();
    Payment addPayment(Payment payment);
    Double updatePayment(Double totalAmount,int user_id);
    Payment deletePayment(Payment payment);
    Payment getPayment(int user_id);
    List<Payment>paymentNumberByUserId(int user_id);

}
