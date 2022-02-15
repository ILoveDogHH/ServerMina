package handler;

import code.OpcodeEnum;
import message.AbstractMessage;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaClientHandler extends IoHandlerAdapter {
    private static HandlerAdapter controllerHandler;

    public MinaClientHandler(){
        controllerHandler = new ControllerHandler();
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
        AbstractMessage<?> messageReceived = (AbstractMessage<?>) message;
        int opcode = messageReceived.getOpcode();
        HandlerAdapter adapter = null;
        OpcodeEnum opcodeEnum = OpcodeEnum.getEnum(opcode);
        switch (opcodeEnum){
            case DefaultReceiveMessage:
                adapter = controllerHandler;
                break;
            default:
                break;
        }
        //具体执行方法
        adapter.execute();
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("客户端与服务端断开连接.....");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        System.out
                .println("one Client Connection" + session.getRemoteAddress());
    }


}