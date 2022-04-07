package message;

import code.OpcodeEnum;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class MessageAdapterImp<T> implements MessageAdapter{

    public MessageReceive<T> getMessage(IoSession ioSession, IoBuffer remain){
        int index = remain.getInt();
        int opcode = remain.getInt();
        OpcodeEnum opcodeEnum = OpcodeEnum.getEnum(opcode);
        switch (opcodeEnum){
            case SendMessage:
                return new ReceiveMessageImp<>(index, OpcodeEnum.ReceiveMessage.opcode, ioSession, remain);
            case ResponseMessage:
                return new ReceiveMessageImp<>(index, OpcodeEnum.ResponseMessage.opcode, ioSession, remain);
            case HeartSendMessage:
                return new HeartReceiveMessage<>(index, OpcodeEnum.HeartReceiveMessage.opcode, ioSession);
            case UnknownMessage:
                return new UnknownReceiveMessage<>(index, opcode, ioSession, remain);
        }
        return new UnknownReceiveMessage<>(index, opcode, ioSession, remain);
    }
}
