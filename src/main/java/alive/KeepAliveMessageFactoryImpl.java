package alive;

import logger.JLogger;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 心跳检测
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {
    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        JLogger.debug("心跳检测区分时候是请求");
        return false;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object o) {
        JLogger.debug("心跳检测区分时候为响应");
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        JLogger.debug("心跳检测获取请求");
        return null;
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        JLogger.debug("心跳检测进型相应");
        return null;
    }


}
