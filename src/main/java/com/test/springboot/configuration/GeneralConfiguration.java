package com.test.springboot.configuration;

import com.test.springboot.bean.MyBean;
import com.test.springboot.bean.implementation.MyBeanTwoImpl;
import com.test.springboot.pojo.properties.UserProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@PropertySource("classpath:connH2test.properties") // Call to new properties file
@EnableConfigurationProperties(UserProperties.class)
public class GeneralConfiguration {

    @Value("${value.name}")
    private String name;

    @Value("${value.random}")
    private String randomValue;

    @Bean
    public MyBean myBean(){
        return new MyBeanTwoImpl(name, randomValue);
    }

    // Injecting DS by bean declaration
    /*@Bean
    public DataSource datasource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/testdb2");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }*/

}
