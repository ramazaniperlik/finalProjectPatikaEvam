package com.finalproject.Service;

import com.finalproject.Entity.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    User deleteUser(int id);
    User updateUser(User user,int id);
    List<User> getUsers();
    User getUserByUserId(int userId);
}
