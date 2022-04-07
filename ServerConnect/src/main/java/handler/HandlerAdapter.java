package handler;

import com.alibaba.fastjson.JSONArray;
import message.AbstractMessage;
import utils.ClassUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Set;

public abstract class HandlerAdapter {

    public  Set<Class<?>> classes;

    public  HashMap<String, Method> method;

    public HandlerAdapter(String pack, Class<?>... params){
        classes = ClassUtil.getClasses(pack);
        method = ClassUtil.getMethod(classes, params);
    }


    public abstract JSONArray execute(AbstractMessage<?> messageReceived);
}
