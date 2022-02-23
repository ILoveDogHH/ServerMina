package message;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;

/**
 * 服务器请求客户段心跳检测
 * @param <T>
 */
public class HeartSendMessage<T> extends SendMessage<T>{
    public HeartSendMessage(int index, int opcode) {
        super(index, opcode);
    }

    @Override
    public IoBuffer toArray() throws CharacterCodingException {
        IoBuffer io = IoBuffer.allocate(10);
        io.setAutoExpand(true);
        io.putInt(index);
        io.putInt(opcode);
        io.flip();
        return io;
    }
}
