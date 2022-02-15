package handler;

import utils.ClassUtil;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class HandlerAdapter {

    public static Set<Class<?>> classes;

    public static ConcurrentHashMap<String, Method> method;

    public void init(String pack){
        classes = ClassUtil.getClasses(pack);
        method = ClassUtil.getMethod(classes);
    }



    abstract void  execute();
}
