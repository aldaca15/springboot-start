package com.test.springboot.controller;

import com.test.springboot.caseuse.CreateUser;
import com.test.springboot.caseuse.DeleteUser;
import com.test.springboot.caseuse.GetUser;
import com.test.springboot.caseuse.UpdateUser;
import com.test.springboot.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final Log LOG = LogFactory.getLog(UserRestController.class);

    private GetUser getUser;
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(GetUser getUser, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    @GetMapping("/")
    List<User> get() {
        return getUser.getAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUser(@PathVariable Long id) {
        User userGet = getUser.getById(id);
        if(userGet != null) {
            return new ResponseEntity<>(userGet, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getFirst")
    User getFirst(HttpServletResponse response) { // Encoding set to UTF-8
        response.setCharacterEncoding("UTF-8");
        return getUser.getAll().get(0);
    }

    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser) {
        User savedUser;
        try {
            savedUser = createUser.save(newUser);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (Exception e) {
            LOG.error(e);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id) {
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> replaceUser(@RequestBody User newUser, @PathVariable Long id) {
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }

    // To check actuator visit: {context}/actuator/health

}