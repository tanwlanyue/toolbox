package com.tanwlanyue.url.reflect;

import com.tanwlanyue.url.service.UserService;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Date;

/**
 * JAVA 反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性； 这种动态获取的信息以及动态调用对象的方法的功能称为 java 语言的反射机制。
 *
 * 优点： 提高代码灵活度。 缺点： 性能瓶颈,安全问题
 *
 * 应用场景 1. 通过反射加载数据库的驱动程序； 2. Spring 框架的 IOC（动态加载管理 Bean）创建对象以及 AOP（动态代理）功能都和反射有联系
 * JDBC利用反射将数据库的表字段映射到java对象的getter/setter方法。 Jackson, GSON, Boon等类库也是利用反射将JSON文件的属性映射到java对的象getter/setter方法。
 * 
 * @author zhanglei211 on 2021/9/16.
 */
public class Demo {

    private int count;

    private Date updateTime;

    private String print(String word) {
        return "hello" + word + " count:" + count + updateTime;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获取class对象 可以Class aa=Demo.class
        Class<?> aClass = Class.forName("com.tanwlanyue.url.reflect.Demo");
        Demo demo = (Demo) aClass.newInstance();
        Method method = aClass.getDeclaredMethod("print", String.class);
        Field field = aClass.getDeclaredField("count");
        field.setAccessible(true);
        field.set(demo, 1);
        Field updateTime = aClass.getDeclaredField("updateTime");
        updateTime.setAccessible(true);
        updateTime.set(demo, new Date());

        method.setAccessible(true);
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        String aaa = (String) method.invoke(demo, "aaa");
        System.out.println(aaa);


        Type type = method.getGenericReturnType();
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            for (Type actualTypeArgument : actualTypeArguments) {
                System.out.println(actualTypeArgument);
            }
        }

        Type[] genericParameterTypes = method.getGenericParameterTypes();
        for (Type genericParameterType : genericParameterTypes) {
            if (genericParameterType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericParameterType).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println(actualTypeArgument);
                }
            }

        }

        // 接口才能使用java的动态代理
        UserService userService = ((UserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[] { UserService.class }, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                }));

    }
}
