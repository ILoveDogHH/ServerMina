package message;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

/**
 * 默认
 */
public class DefaultReceiveMessage<T> extends ReceiveMessage<T>{
    //方法名字
    String funName;
    //方法参数
    String funParams;

    public DefaultReceiveMessage(int index, int opcode, IoSession ioSession, IoBuffer ioBuffer) {
        super(index, opcode, ioSession, ioBuffer);
    }

    @Override
    public void getRemain(IoBuffer ioBuffer) {

    }
}
