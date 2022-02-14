package message;

import code.OpcodeEnum;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class MessageAdapterImp<T> implements MessageAdapter{

    public AbstractMessage<T> getMessage(IoSession ioSession, IoBuffer remain){
        int index = remain.getInt();
        int opcode = remain.getInt();
        OpcodeEnum opcodeEnum = OpcodeEnum.getEnum(opcode);
        switch (opcodeEnum){
            case DefaultReceiveMessage:
                return new DefaultReceiveMessage<>(index, opcode, ioSession, remain);
            case UnknownReceiveMessage:
                return new UnknownReceiveMessage<>(index, opcode, ioSession, remain);
        }
        return new UnknownReceiveMessage<>(index, opcode, ioSession, remain);
    }
}
