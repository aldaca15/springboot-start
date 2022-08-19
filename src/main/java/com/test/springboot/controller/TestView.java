package com.test.springboot.controller;

import com.test.springboot.entity.User;
import com.test.springboot.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TestView {

    private UserService userService;

    public TestView(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<String> function(){
        return new ResponseEntity<>("hello everyone", HttpStatus.OK);
    }

    @GetMapping("/view")
    public String message() {
        return "viewtest";
    }

    // To check actuator visit: {context}/actuator/health

    @RequestMapping("/userslist")
    public String usersList(Model model) {
        List<User> usersApi = userService.getAllUsers();
        model.addAttribute("listuserview", usersApi);
        return "listuserview";
    }

    @RequestMapping("/userslist2")
    public String usersList2(Model model) {
        List<User> usersApi = userService.getUsersFromRest();
        model.addAttribute("listuserview", usersApi);
        return "listuserview";
    }

}
