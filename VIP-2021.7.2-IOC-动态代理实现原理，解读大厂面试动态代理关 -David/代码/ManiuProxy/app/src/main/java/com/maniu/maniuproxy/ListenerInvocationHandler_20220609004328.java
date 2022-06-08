package com.maniu.maniuproxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
//处理器
public class ListenerInvocationHandler implements InvocationHandler {
//    MainActivity  张三
    private Object context;
    private Method activityMethod;

    public ListenerInvocationHandler(Object context, Method activityMethod) {
        this.context = context;
        this.activityMethod = activityMethod;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.i("david", "invoke: ----调用前");
        return activityMethod.invoke(context, args);
    }
}
