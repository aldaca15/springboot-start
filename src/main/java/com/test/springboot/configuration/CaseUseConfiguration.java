package com.test.springboot.configuration;

import com.test.springboot.caseuse.GetUser;
import com.test.springboot.caseuse.GetUserImplement;
import com.test.springboot.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService) {
        return new GetUserImplement(userService);
    }

}
