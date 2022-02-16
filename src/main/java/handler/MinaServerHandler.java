package handler;

import code.OpcodeEnum;
import message.AbstractMessage;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

public class MinaServerHandler extends IoHandlerAdapter {

    private static HandlerAdapter controllerHandler;
    private static HandlerAdapter errorHandler;

    public MinaServerHandler(){
        controllerHandler = new ControllerHandler("controller");
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
        adapter.execute(messageReceived);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionClosed(session);
        System.out.println("客户端与服务端断开连接.....");
    }

}
