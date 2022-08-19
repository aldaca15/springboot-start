package com.test.springboot.caseuse;

import com.test.springboot.entity.User;
import com.test.springboot.services.UserService;

import java.util.List;

public class GetUserImplement implements GetUser {

    private final UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    /*@Override
    public User getById(Long idUser) {
        return userService.getById(idUser);
    }*/

    @Override
    public User getById(Long idUser) {
        return userService.getById(idUser);
    }

}
