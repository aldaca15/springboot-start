package com.test.springboot.entity;

public class EntityX {
    private String key;
    private String value;

    public EntityX(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String id) {
        this.key = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}