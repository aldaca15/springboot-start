package com.test.springboot.caseuse;

import com.test.springboot.entity.User;

import java.util.List;

public interface GetUser {
    List<User> getAll();

    User getById(Long idUser);

    //User getById(Integer idUser);
}
