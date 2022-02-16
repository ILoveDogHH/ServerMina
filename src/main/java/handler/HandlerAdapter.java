package handler;

import message.AbstractMessage;
import utils.ClassUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class HandlerAdapter {

    public static Set<Class<?>> classes;

    public static HashMap<String, Method> method;

    public HandlerAdapter(String pack){
        classes = ClassUtil.getClasses(pack);
        method = ClassUtil.getMethod(classes);
    }


    abstract void  execute(AbstractMessage<?> messageReceived);
}
