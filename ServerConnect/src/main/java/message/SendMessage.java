package message;


public abstract class SendMessage<T> extends AbstractMessage<T> implements MessageSend{


    public SendMessage(int index, int opcode) {
        super(index, opcode);
    }
}
