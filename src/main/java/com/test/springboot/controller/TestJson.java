package com.test.springboot.controller;

import com.test.springboot.entity.EntityX;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // if not present and @controller present, eqully produces json
//@Controller
public class TestJson {

    @GetMapping("/json")
    public ResponseEntity<EntityX> result(){
        return new ResponseEntity<>(new EntityX("Status","OK"), HttpStatus.OK);
    }

    // To check actuator visit: {context}/actuator/health

}
