package com.finalproject.Repository;

import com.finalproject.Entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoHibernate implements IUserDao{

    private final EntityManager entityManager;

    public UserDaoHibernate(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User addUser(User user) {
        Session session =  entityManager.unwrap(Session.class);
        session.save(user);
        return user;
    }

    @Override
    public User deleteUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.delete(user);
        return user;
    }

    @Override
    public User updateUser(User user,int id) {
        Session session = entityManager.unwrap(Session.class);
        User user2 = session.get(User.class,id);
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        session.update(user2);
        return user2;
    }

    @Override
    public List<User> getUsers() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from User u",User.class).getResultList();
    }

    @Override
    public User getUserByUserId(int id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class,id);
    }
}
