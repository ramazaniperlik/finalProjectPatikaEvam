package com.finalproject.Repository;

import com.finalproject.Entity.Invoice;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class InvoiceDaoHibernate implements IInvoiceDao{

    private final EntityManager entityManager;

    public InvoiceDaoHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        Session session = entityManager.unwrap(Session.class);
        session.save(invoice);
        return invoice;
    }

    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(invoice);
        return invoice;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice,int invoiceId) {
        Session session = entityManager.unwrap(Session.class);
        session.createQuery("update Invoice i set i.invoiceAmount=:invoiceAmount where i.id = :id")
                .setParameter("id",invoiceId)
                .setParameter("invoiceAmount",invoice.getInvoiceAmount())
                .executeUpdate();
        return getInvoiceByInvoiceId(invoiceId);
    }

    @Override
    public List<Invoice> getInvoiceList() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Invoice i",Invoice.class).getResultList();
    }

    @Override
    public Invoice getInvoiceByInvoiceId(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Invoice i where i.id = :id",Invoice.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public List<Invoice> getInvoiceListByUserId(int user_id){
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Invoice i where i.user.id =:user_id",Invoice.class)
                .setParameter("user_id",user_id)
                .getResultList();
    }


    @Override//updates the status field on the invoice to 1 after payment.
    public Invoice changeStatuToPaidInvoice(int invoiceId){
        Session session = entityManager.unwrap(Session.class);
        session.createQuery("update Invoice i set i.statu=1 where i.id=:invoiceId")
                .setParameter("invoiceId",invoiceId)
                .executeUpdate();
        return getInvoiceByInvoiceId(invoiceId);

    }

    @Override
    public Invoice isPaidInvoice(int userId, int invoiceId) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Invoice i where i.id=:invoiceId and i.user.id=:userId",Invoice.class)
                .setParameter("invoiceId",invoiceId)
                .setParameter("userId",userId)
                .getSingleResult();
    }
}
