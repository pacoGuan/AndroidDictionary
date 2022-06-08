package com.maniu.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

public class MyClass {
    public static void main1(String[] args) throws IOException {
        byte[] bytes = ProxyGenerator.generateProxyClass("DavidHelloImpl", new Class[]{HelloInterface.class});
        File file = new File("C:\\Users\\Administrator\\Downloads\\ManiuProxy\\proxy\\src\\main\\java\\com\\maniu\\proxy\\DavidHelloImpl.class");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
    public static void main(String[] args) {
        Hello hello = new Hello();
        HelloInterface helloInterface= (HelloInterface) Proxy.newProxyInstance(hello.getClass()
                        .getClassLoader(),
                hello.getClass().getInterfaces(),
                new ProxyHandler(hello));
        helloInterface.sayHello();
    }
//    David代购
    static class ProxyHandler implements InvocationHandler {
//        代理对象   张三
        private Object object;
        public ProxyHandler(Object object){
            this.object = object;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("david--------->S");
            method.invoke(object, args);
            System.out.println("david--------->S2");
            return null;
        }
    }
}
