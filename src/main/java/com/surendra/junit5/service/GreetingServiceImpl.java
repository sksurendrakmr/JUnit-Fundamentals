package com.surendra.junit5.service;

public class GreetingServiceImpl implements GreetingService{
    @Override
    public String greet(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException();
        }
        return "Hello " + name;
    }
}
