package message;

import org.apache.mina.core.session.IoSession;

import java.nio.charset.CharacterCodingException;

/**
 * 客户端响应服务器心跳检测
 */
public class HeartReceiveMessage<T> extends  ReceiveMessage<T>{
    public HeartReceiveMessage(int index, int opcode, IoSession ioSession) {
        super(index, opcode,ioSession, null);
    }

    @Override
    public void getRemain() throws CharacterCodingException {

    }


}
