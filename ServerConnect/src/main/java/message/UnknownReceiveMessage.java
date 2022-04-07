package message;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class UnknownReceiveMessage<T> extends ReceiveMessage<T>{

    public UnknownReceiveMessage(int index, int opcode, IoSession ioSession, IoBuffer ioBuffer) {
        super(index, opcode, ioSession, ioBuffer);
    }

    @Override
    public void getRemain() {

    }
}
