package com.finalproject.Service;

import com.finalproject.Entity.Invoice;
import com.finalproject.Entity.Payment;
import com.finalproject.Entity.User;
import com.finalproject.Repository.IInvoiceDao;
import com.finalproject.Repository.IPaymentDao;
import com.finalproject.Repository.IUserDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService implements IInvoiceService{

    private final IInvoiceDao invoiceDao;
    private final IUserDao userDao;

    public InvoiceService(IInvoiceDao invoiceDao, IUserDao userDao, IPaymentDao paymentDao) {
        this.invoiceDao = invoiceDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice,int user_id) {
        User User = userDao.getUserByUserId(user_id);
        Invoice newInvoice = new Invoice(invoice.getInvoiceAmount(), Date.valueOf(LocalDate.now()),"0",User);
        return invoiceDao.addInvoice(newInvoice);
    }

    @Override
    @Transactional
    public Invoice deleteInvoice(int id) {
        Invoice result = invoiceDao.getInvoiceByInvoiceId(id);
        return invoiceDao.deleteInvoice(result);
    }

    @Override
    @Transactional
    public Invoice updateInvoice(Invoice invoice,int invoiceId) {
        return invoiceDao.updateInvoice(invoice,invoiceId);
    }

    @Override
    @Transactional
    public List<Invoice> getInvoiceList() {
        return invoiceDao.getInvoiceList();
    }

    @Override
    @Transactional
    public Invoice getInvoiceByInvoiceId(int id) {
        return invoiceDao.getInvoiceByInvoiceId(id);
    }

    @Override
    @Transactional
    public Invoice changeStatuToPaidInvoice(int invoiceId){
        return invoiceDao.changeStatuToPaidInvoice(invoiceId);
    }

    @Override
    public boolean isPaidInvoice(int userId, int invoiceId) {
        Invoice result =invoiceDao.isPaidInvoice(userId,invoiceId);
        return !result.getStatu().equals("0");
    }
}
