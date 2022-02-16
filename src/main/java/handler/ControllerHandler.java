package handler;

import com.alibaba.fastjson.JSONArray;
import exception.DefaultException;
import logger.JLogger;
import message.AbstractMessage;
import message.DefaultReceiveMessage;

import java.lang.reflect.Method;

public class ControllerHandler extends HandlerAdapter{


    public ControllerHandler(String pack) {
        super(pack);
    }

    @Override
    void execute(AbstractMessage<?> messageReceived) {
        DefaultReceiveMessage<String> receiveMessage = (DefaultReceiveMessage<String>) messageReceived;
        int uid = receiveMessage.getUid();
        String funName = receiveMessage.getFunName();
        try {
            Method method = this.method.get(funName);
            if(method == null){
                throw new DefaultException(String.format("no functuin %s", funName));
            }
            method.invoke(null, uid, receiveMessage.getFunParams(), receiveMessage.getIosession());
        }catch (Exception e){
            JLogger.error(String.format("uid:%d--function:%s error", uid, funName), e);
        }

    }
}
