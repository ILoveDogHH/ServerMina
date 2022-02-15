package message;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 * 接收数据进行请求
 * @param <T>
 */
public abstract class ReceiveMessage<T> extends AbstractMessage<T> implements MessageReceive{
    //玩家的session信息
    IoSession ioSession;
    //io剩余信息
    IoBuffer remain;

    public ReceiveMessage(int index, int opcode, IoSession ioSession, IoBuffer ioBuffer) {
        super(index, opcode);
        this.ioSession = ioSession;
        this.remain = ioBuffer;
    }



}
