package com.finalproject.Repository;

import com.finalproject.Entity.Invoice;

import java.util.List;

public interface IInvoiceDao {
    Invoice addInvoice(Invoice invoice);
    Invoice deleteInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice,int incoiceId);
    Invoice getInvoiceByInvoiceId(int id);
    Invoice changeStatuToPaidInvoice(int invoiceId);
    Invoice isPaidInvoice(int userId, int invoiceId);
    List<Invoice> getInvoiceList();
    List<Invoice> getInvoiceListByUserId(int userId);
}
