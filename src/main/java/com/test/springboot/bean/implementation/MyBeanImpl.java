package com.test.springboot.bean.implementation;

import com.test.springboot.bean.MyBean;

public class MyBeanImpl implements MyBean {

    @Override
    public String hello() {
        return "Hello from my BeanImpl";
    }
}
