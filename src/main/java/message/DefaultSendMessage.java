package message;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * 默认发送消息
 */
public class DefaultSendMessage<T> extends SendMessage<String>{
    public DefaultSendMessage(int index, int opcode, int uid, String data) {
        super(index, opcode);
        this.uid = uid;
        this.data = data;
    }

    @Override
    public IoBuffer toArray() throws CharacterCodingException {
        IoBuffer ioBuffer = IoBuffer.allocate(10);
        ioBuffer.setAutoExpand(true);
        ioBuffer.putInt(index);
        ioBuffer.putInt(opcode);
        ioBuffer.putInt(uid);
        ioBuffer.putPrefixedString(data, Charset.forName("UTF-8").newEncoder());
        ioBuffer.flip();
        return ioBuffer;
    }


}
