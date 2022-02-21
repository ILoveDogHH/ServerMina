package message;

import code.OpcodeEnum;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class MessageAdapterImp<T> implements MessageAdapter{

    public ReceiveMessage<T> getMessage(IoSession ioSession, IoBuffer remain){
        int index = remain.getInt();
        int opcode = remain.getInt();
        OpcodeEnum opcodeEnum = OpcodeEnum.getEnum(opcode);
        switch (opcodeEnum){
            case DefaultReceiveMessage:
                return new DefaultReceiveMessage<>(index, opcode, ioSession, remain);
            case DefaultSendMessage:
                return new ClientReceiveMessage<>(index, opcode, ioSession, remain);
            case KeepSendMessage:
                return new HeartReceiveMessage<>(index, opcode, ioSession, remain);
            case UnknownMessage:
                return new UnknownReceiveMessage<>(index, opcode, ioSession, remain);
        }
        return new UnknownReceiveMessage<>(index, opcode, ioSession, remain);
    }
}
