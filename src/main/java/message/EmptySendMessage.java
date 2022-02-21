package message;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;

public class EmptySendMessage<T> extends SendMessage<T>{
    public EmptySendMessage(int index, int opcode) {
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
