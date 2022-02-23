package message;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public interface MessageAdapter<T> {

    MessageReceive<T> getMessage(IoSession ioSession, IoBuffer remain);


}
