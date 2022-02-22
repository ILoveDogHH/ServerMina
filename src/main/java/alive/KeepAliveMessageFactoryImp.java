package alive;

import code.OpcodeEnum;
import logger.JLogger;
import message.HeartReceiveMessage;
import message.HeartSendMessage;
import message.SendMessage;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 心跳检测
 */
public class KeepAliveMessageFactoryImp implements KeepAliveMessageFactory {



    @Override
    public boolean isRequest(IoSession ioSession, Object obj) {
        if(obj instanceof HeartSendMessage<?>){
            return true;
        }
        return false;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object obj) {
        if(obj instanceof HeartReceiveMessage<?>){
            return true;
        }
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        JLogger.debug("心跳检测服务器发起");
        return new HeartSendMessage<>(1, OpcodeEnum.KeepSendMessage.opcode);
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        JLogger.debug("心跳检测响应陈宫");
        return new HeartReceiveMessage<>(1, OpcodeEnum.KeepReceiveMessage.opcode, ioSession);
    }






}
