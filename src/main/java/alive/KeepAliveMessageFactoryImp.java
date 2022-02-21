package alive;

import code.OpcodeEnum;
import logger.JLogger;
import message.EmptySendMessage;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 心跳检测
 */
public class KeepAliveMessageFactoryImp implements KeepAliveMessageFactory {



    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        JLogger.debug("心跳检测区分时候是请求");
        return true;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object o) {
        JLogger.debug("心跳检测区分时候为响应");
        return true;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        return new EmptySendMessage<>(1, OpcodeEnum.KeepSendMessage.opcode);
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        //心跳检测响应事件
        JLogger.debug("响应事件");
        return null;
    }






}
