package com.tanwlanyue.ioc;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.tanwlanyue.ioc.annotation.Autowired;
import com.tanwlanyue.ioc.annotation.Service;
import com.tanwlanyue.ioc.service.impl.GoodsServiceImpl;
import com.tanwlanyue.ioc.service.impl.StockServiceImpl;

/**
 * @author zhanglei211 on 2021/9/18.
 */
public class ApplicationContext {

    private String scanPackageName;
    private ConcurrentHashMap<String, Object> beans;

    public ApplicationContext(String scanPackageName) throws Exception {
        this.beans = new ConcurrentHashMap<>();
        this.scanPackageName = scanPackageName;
        initBeans();
        initEntryField();
    }

    private void initEntryField() throws Exception {
        // 1.遍历所有的bean容器对象
        for (Map.Entry<String, Object> entry : beans.entrySet()) {
            // 2.判断属性上面是否有加注解
            Object bean = entry.getValue();
            attrAssign(bean);
        }
    }

    /**
     * 反射包下所有类，判断是否有注解
     */
    private void initBeans() throws Exception {
        // 1.使用java的反射机制扫包,[获取当前包下所有的类]
        List<Class<?>> classes = Lists.newArrayList(GoodsServiceImpl.class, StockServiceImpl.class);
        // 2、判断类上面是否存在注入的bean的注解
        ConcurrentHashMap<String, Object> classExistAnnotation = findClassExistAnnotation(classes);
        if (classExistAnnotation == null || classExistAnnotation.isEmpty()) {
            throw new Exception("该包下没有任何类加上注解");
        }
    }

    public ConcurrentHashMap<String, Object> findClassExistAnnotation(List<Class<?>> classes) throws Exception {
        for (Class<?> classInfo : classes) {
            Service annotation = classInfo.getAnnotation(Service.class);
            if (annotation != null) {
                // 获取当前类的类名
                String className = classInfo.getSimpleName();
                String beanId = toLowerCaseFirstOne(className);
                // 通过反射进行实列化
                Object newInstance = newInstance(classInfo);
                beans.put(beanId, newInstance);
            }

        }
        return beans;
    }

    public String toLowerCaseFirstOne(String s) {
        return Character.isLowerCase(s.charAt(0)) ? s : Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    public Object newInstance(Class<?> classInfo)
            throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return classInfo.newInstance();
    }

    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)) {
            throw new Exception("beanId参数不能为空");
        }
        return beans.get(beanId);
    }

    public void attrAssign(Object object) throws Exception {
        Class<? extends Object> classInfo = object.getClass();
        Field[] declaredFields = classInfo.getDeclaredFields();
        for (Field field : declaredFields) {
            Autowired extResource = field.getAnnotation(Autowired.class);
            if (extResource != null) {
                String beanId = field.getName();
                Object bean = getBean(beanId);
                if (bean != null) {
                    field.setAccessible(true);
                    field.set(object, bean);
                }
            }
        }

    }
}
