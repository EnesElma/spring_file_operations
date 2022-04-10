package com.enes.user.service;

import com.enes.user.entity.User;

import java.util.Optional;

public interface IUserService {
    User saveUser(User user);

    Optional<User> findUser(long id);
}
