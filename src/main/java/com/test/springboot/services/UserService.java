package com.test.springboot.services;

import com.test.springboot.entity.User;
import com.test.springboot.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

//import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void saveUser(User u) {
        User userWithEmail = userRepository.findByUserEmail(u.getEmail()).orElse(null);
        if(userWithEmail != null) {
            LOG.error("User with the following email, already exists " + u.getEmail());
            //new Exception("Exception, email exists");
        } else {
            userRepository.save(u);
        }
    }

    @Transactional//(readOnly = false, isolation= Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
    //@Transactional(rollbackFor=Exception.class, propagation=Propagation.REQUIRED)
    public void saveTransactional(List<User> us) {
        for (User u: us) {
            this.saveUser(u);
        }
        //us.stream().peek(user -> System.out.print("Usuario insertado: " + user)).forEach(userRepository::save); //
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersFromRest() {
        RestTemplate plantilla = new RestTemplate();
        User[] facturas = plantilla.getForObject("http://localhost:8081/testboot/api/users/", User[].class);
        List<User> lista = Arrays.asList(facturas);
        return lista;
    }

    public User save(User newUser) {
        User userFind = userRepository.findByUserEmail(newUser.getEmail()).orElse(null);
        if(userFind != null) {
            LOG.error("User with the following email, already exists " + newUser.getEmail());
            return null;
        }
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id)
        //return userRepository.findById(id)
                .map(
                    user -> {
                        user.setEmail(newUser.getEmail());
                        user.setBirthDate(newUser.getBirthDate());
                        user.setName(newUser.getName());
                        return userRepository.save(user);
                    })
                .get();
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
