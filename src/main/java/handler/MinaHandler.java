package handler;

import code.OpcodeEnum;
import com.alibaba.fastjson.JSONArray;
import logger.JLogger;
import message.AbstractMessage;
import message.DefaultSendMessage;
import message.MessageSend;
import message.ReceiveMessage;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaHandler extends IoHandlerAdapter {

    private static HandlerAdapter controllerHandler;
    private static HandlerAdapter errorHandler;

    public MinaHandler(){
        controllerHandler = new ControllerHandler("controller", int.class, JSONArray.class, IoSession.class);
    }


    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        cause.printStackTrace();
    }

    /*
     * 这个方法是目前这个类里最主要的，
     * 当接收到消息，只要不是quit，就把服务器当前的时间返回给客户端
     * 如果是quit，则关闭客户端连接
     * */
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        ReceiveMessage<?> messageReceived = (ReceiveMessage<?>) message;
        int opcode = messageReceived.getOpcode();
        HandlerAdapter adapter = null;
        OpcodeEnum opcodeEnum = OpcodeEnum.getEnum(opcode);
        switch (opcodeEnum){
            case DefaultReceiveMessage:
                adapter = controllerHandler;
                break;
            default:
                adapter = errorHandler;
                break;
        }
        //具体执行方法
        JSONArray result = adapter.execute(messageReceived);
        callbackResult(messageReceived, result);
    }

    public void callbackResult(ReceiveMessage<?> message, JSONArray result){
        try {
            int opcode = message.getOpcode();
            AbstractMessage abstractMessage = null;
            OpcodeEnum opcodeEnum = OpcodeEnum.getEnum(opcode);
            switch (opcodeEnum){
                case DefaultReceiveMessage:
                    abstractMessage = new DefaultSendMessage(0, OpcodeEnum.DefaultSendMessage.opcode, message.getUid(), result.toJSONString());
                    break;
                default:
                    abstractMessage = new DefaultSendMessage(0, OpcodeEnum.UnknownMessage.opcode, message.getUid(), result.toJSONString());
                    break;
            }
            message.getIoSession().write(abstractMessage);
        }catch (Exception e){
            JLogger.error("callbackResult error", e);
        }
    }


    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionClosed(session);
        System.out.println("客户端与服务端断开连接.....");
    }

}
