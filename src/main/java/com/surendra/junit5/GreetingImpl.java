package com.surendra.junit5;

import com.surendra.junit5.service.GreetingService;

public class GreetingImpl implements Greeting{
    private GreetingService service;

    @Override
    public String greet(String name) {
        return service.greet(name);
    }
}
