package com.finalproject.Repository;

import com.finalproject.Entity.Payment;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PaymentDaoHibernate implements IPaymentDao{

    private final EntityManager entityManager;

    public PaymentDaoHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Payment> getPayments() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Payment",Payment.class).getResultList();
    }

    @Override
    public Payment addPayment(Payment payment) {
        Session session = entityManager.unwrap(Session.class);
        session.save(payment);
        return payment;
    }

    @Override
    public Double updatePayment(Double totalAmount,int user_id){
        Session session = entityManager.unwrap(Session.class);
        Payment payment = getPayment(user_id);
        Double lastTotalAmount = payment.getTotalAmount()+totalAmount;
        session.createQuery("update Payment p set p.totalAmount=:totalAmount where p.user.id=:user_id")
                 .setParameter("user_id",user_id)
                 .setParameter("totalAmount",lastTotalAmount)
                 .executeUpdate();
        return totalAmount;
    }

     //If there is a record in the payment table according to the user Id,
    // it is added to the list. The list returned by this method is used to distinguish
    // between adding and updating payment. If there is a record, update it, if not, add it.
     @Override
    public List<Payment> paymentNumberByUserId(int user_id) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Payment p where p.user.id=:user_id",Payment.class)
                    .setParameter("user_id",user_id)
                    .getResultList();
        }

    @Override
    public Payment getPayment(int user_id) {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Payment p where p.user.id=:user_id",Payment.class)
                    .setParameter("user_id",user_id)
                    .getSingleResult();
  }

    @Override
    public Payment deletePayment(Payment payment){
        Session session = entityManager.unwrap(Session.class);
        session.delete(payment);
        return payment;
    }
    }
