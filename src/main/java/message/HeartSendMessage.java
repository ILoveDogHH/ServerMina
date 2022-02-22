package message;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;

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
