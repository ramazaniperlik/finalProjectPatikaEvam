package com.finalproject.Service;

import com.finalproject.Entity.User;
import com.finalproject.Repository.IUserDao;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public User addUser(User user) {
       return userDao.addUser(user);
    }

    @Override
    @Transactional
    public User deleteUser(int id) {
        User user = userDao.getUserByUserId(id);
        return userDao.deleteUser(user);
    }

    @Override
    @Transactional
    public User updateUser(User user,int id) {
        return userDao.updateUser(user,id);
    }

    @Override
    @Transactional
    public List<User> getUsers() { return userDao.getUsers();}

    @Override
    @Transactional
    public User getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }
}
