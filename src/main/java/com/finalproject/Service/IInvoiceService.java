package com.finalproject.Service;

import com.finalproject.Entity.Invoice;

import java.util.List;

public interface IInvoiceService {
    Invoice addInvoice(Invoice invoice, int user_id);
    Invoice deleteInvoice(int id);
    Invoice updateInvoice(Invoice invoice,int invoiceId);
    Invoice getInvoiceByInvoiceId(int id);
    List<Invoice> getInvoiceList();
    Invoice changeStatuToPaidInvoice(int invoiceId);
    boolean isPaidInvoice(int userId, int invoiceId);
}
