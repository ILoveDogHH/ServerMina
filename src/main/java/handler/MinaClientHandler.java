package handler;

import code.OpcodeEnum;
import com.alibaba.fastjson.JSONArray;
import logger.JLogger;
import message.AbstractMessage;
import message.DefaultSendMessage;
import message.ReceiveMessage;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaClientHandler extends IoHandlerAdapter {

    private HandlerAdapter HeartHandler ;

    public MinaClientHandler(){
        HeartHandler = new VoidHandler(null, null);
    }



    // 当客户端连接进入时
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("incomming 客户端: " + session.getRemoteAddress());
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println(cause);
    }

    // 当客户端发送消息到达时
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        ReceiveMessage<?> messageReceived = (ReceiveMessage<?>) message;
        int opcode = messageReceived.getOpcode();
        HandlerAdapter adapter = null;
        OpcodeEnum opcodeEnum = OpcodeEnum.getEnum(opcode);
        switch (opcodeEnum){
            case KeepSendMessage:
                adapter = HeartHandler;
                break;
            default:
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
                case KeepSendMessage:
                    abstractMessage = new DefaultSendMessage(0, OpcodeEnum.KeepReceiveMessage.opcode, message.getUid(), result.toJSONString());
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
        session.close(true);
        System.out.println("客户端与服务端断开连接.....");
        //session 关闭后的操作

    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        System.out
                .println("one Client Connection" + session.getRemoteAddress());
    }


}