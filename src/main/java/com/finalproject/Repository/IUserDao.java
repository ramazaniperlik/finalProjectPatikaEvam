package com.finalproject.Repository;

import com.finalproject.Entity.User;

import java.util.List;

public interface IUserDao {
    User addUser(User user);
    User deleteUser(User user);
    User updateUser(User user,int id);
    List<User> getUsers();
    User getUserByUserId(int id);
}
