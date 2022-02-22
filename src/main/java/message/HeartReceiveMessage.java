package message;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.nio.charset.CharacterCodingException;

/**
 * 心跳检测接受消息message
 * @param <T>
 */
public class HeartReceiveMessage<T> extends  ReceiveMessage<T>{
    public HeartReceiveMessage(int index, int opcode, IoSession ioSession) {
        super(index, opcode,ioSession, null);
    }

    @Override
    public void getRemain() throws CharacterCodingException {

    }
}
