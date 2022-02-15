package message;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public interface MessageAdapter<T> {

    ReceiveMessage<T> getMessage(IoSession ioSession, IoBuffer remain);


}
