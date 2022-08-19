package com.test.springboot.caseuse;

import com.test.springboot.entity.User;
import com.test.springboot.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UpdateUser {
    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(User newUser, Long id) {
        return userService.update(newUser, id);
    }
}
