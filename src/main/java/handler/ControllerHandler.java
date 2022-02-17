package handler;

import cmdReturn.CmdReturn;
import com.alibaba.fastjson.JSONArray;
import exception.DefaultException;
import logger.JLogger;
import message.AbstractMessage;
import message.DefaultReceiveMessage;

import java.lang.reflect.Method;

public class ControllerHandler extends HandlerAdapter{


    public ControllerHandler(String pack, Class<?>... params) {
        super(pack, params);
    }

    @Override
    public JSONArray execute(AbstractMessage<?> messageReceived) {
        String errorMsg = "";
        try {
            DefaultReceiveMessage<String> receiveMessage = (DefaultReceiveMessage<String>) messageReceived;
            int uid = receiveMessage.getUid();
            String funName = receiveMessage.getFunName();
            try {
                Method method = this.method.get(funName);
                if(method == null){
                    throw new DefaultException(String.format("no functuin %s", funName));
                }
                long start = System.currentTimeMillis();
                JSONArray result = (JSONArray) method.invoke(null, uid, receiveMessage.getFunParams(), receiveMessage.getIosession());
                long end = System.currentTimeMillis();
                long cost = end - start;
                int code = result.getInteger(0);
                JLogger._daylog("request", "function:{},params:{},cost:{},code:{}",funName, receiveMessage.getFunParams(), cost, code);
                return result;
                //服务器进行推送下
            }catch (Exception e){
                JLogger.error(String.format("uid:%d--function:%s error", uid, funName), e);
                errorMsg = String.format("uid:%d--function:%s error:%s", uid, funName,e.getMessage());
            }
        }catch (Exception e){
            JLogger.error("ControllerHandler error", e);
            errorMsg = String.format("ControllerHandler error",e);
        }
        return CmdReturn.error(errorMsg);
    }

}
