package com.maniu.proxy;

public class Hello implements HelloInterface{
    @Override
    public void sayHello() {
        System.out.println("Hello david!");
    }
}