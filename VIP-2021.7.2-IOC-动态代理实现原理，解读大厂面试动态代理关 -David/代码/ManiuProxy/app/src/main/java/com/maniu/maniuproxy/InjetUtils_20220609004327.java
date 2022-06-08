package com.maniu.maniuproxy;

import android.view.View;

import com.maniu.maniuproxy.annotion.ContentView;
import com.maniu.maniuproxy.annotion.EventBase;
import com.maniu.maniuproxy.annotion.OnClick;
import com.maniu.maniuproxy.annotion.ViewInject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InjetUtils {
    private static final String TAG = "maniu";
//注入方法  ioc  苦逼
    public static void  inject(Object context)
    {
        //setContentView  的逻辑
        injectLayout(context);
        injectView(context);
        injectClick(context);
    }

    private static void injectClick(Object context) {
        Class<?> clazz = context.getClass();
//        反射  zhujie
        Method[] methods=clazz.getDeclaredMethods();
        for (Method method : methods) {
//            可以    架构 20 个事件类型
//            OnClick onClick= method.getAnnotation(OnClick.class);
//            EventBase eventBase= onClick.getClass().getAnnotation(EventBase.class);
            Annotation[] annotations= method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<?> annotionType=annotation.annotationType();
                EventBase eventBase=annotionType.getAnnotation(EventBase.class);
                if (eventBase == null) {
                    continue;
                }
//                拿到事件三要素
                /**
                 * setOnLongClickListener
                 */
                String listenerSetter = eventBase.listenerSetter();
//OnLongClickListener.class
                Class<?> listenerType = eventBase.listenerType();
                /**
                 * 事件被触发之后，执行的回调方法的名称
                 * @return
                 *
                 * onLongClick
                 */
                String callBackMethod = eventBase.callbackMethod();

//                id'
                try {
                    Method valueMethod=annotionType.getDeclaredMethod("value");
                    int[] viewId= (int[]) valueMethod.invoke(annotation);
                    for (int id : viewId) {
//                        有多少个按钮 id ---》findviewById   View.setOnClickerListener
                        Method findViewById=clazz.getMethod("findViewById",int.class);
                        View view = (View) findViewById.invoke(context, id);
                        ListenerInvocationHandler listenerInvocationHandler = new ListenerInvocationHandler(context, method);
                       Method  setListener=view.getClass().getMethod(listenerSetter,listenerType);
                        Object proxy = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType}, listenerInvocationHandler);
                        setListener.invoke(view, proxy);
//                        view.setOnClickListener(动态代理);
//                        view.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                            }
//                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            EventBase   --

            /**
             * btn  button    ====> value  R.id.btn  ===>findviewbyid
             *      btn.setOnClickListener(new View.OnClickListener() {
             *             @Override
             *             public void onClick(View v) {
             *
             *             }
             *         });
             */
//            setOnClickListener   Method  设置
//

        }



    }

    private static void injectView(Object context) {
//        反射
        Class<?> aClass = context.getClass();

        Field[] fields=aClass.getDeclaredFields();

        for (Field field : fields) {
            ViewInject viewInject = field.getAnnotation(ViewInject.class);
            if (viewInject == null) {
                continue;
            }
//            ID
            int valueId = viewInject.value();
            try {
                Method method=aClass.getMethod("findViewById",int.class);
                View view= (View) method.invoke(context, valueId);
//                view   btn  产生联系
                field.setAccessible(true);
                field.set(context, view);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void injectLayout(Object context) {
        int layouyId = 0;
        Class<?> clazz = context.getClass();
        ContentView contentView=clazz.getAnnotation(ContentView.class);
        if (contentView == null) {
            return;
        }
        layouyId = contentView.value();
//        反射
        try {
            Method contentMethod=context.getClass().getMethod("setContentView",int.class);
            contentMethod.invoke(context, layouyId);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
