package com.finalproject.Service;

import com.finalproject.Entity.Invoice;
import com.finalproject.Entity.Payment;
import com.finalproject.Entity.User;
import com.finalproject.Repository.IPaymentDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class PaymentService implements IPaymentService{

    private final IPaymentDao paymentDao;
    private final IInvoiceService invoiceDao;
    private final IUserService userDao;

    public PaymentService(IPaymentDao paymentDao, IInvoiceService invoiceDao, IUserService userDao) {
        this.paymentDao = paymentDao;
        this.invoiceDao = invoiceDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<Payment> getPayments() {
        return paymentDao.getPayments();
    }

    @Override
    @Transactional
    public boolean payInvoice(int invoiceId){

        Invoice resultInvoice = invoiceDao.getInvoiceByInvoiceId(invoiceId);

        if(resultInvoice.getStatu().equals("0")){

            invoiceDao.changeStatuToPaidInvoice(invoiceId);
            User User = userDao.getUserByUserId(resultInvoice.getUser().getId());
            double total_amount = Double.parseDouble(resultInvoice.getInvoiceAmount());
            Payment newPayment = new Payment(total_amount, Date.valueOf(LocalDate.now()),User);

            if(paymentDao.paymentNumberByUserId(User.getId()).size()>0)
                paymentDao.updatePayment(total_amount,User.getId());
            else
                paymentDao.addPayment(newPayment);
            return true;
        } else
            return false;
    }

}
