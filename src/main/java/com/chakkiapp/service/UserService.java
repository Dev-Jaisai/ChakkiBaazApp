package com.chakkiapp.service;

import com.chakkiapp.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);

}
