package message;

public abstract class  AbstractMessage<T> implements Message {

    int index;

    int opcode;

    int uid;

    T data;

    public AbstractMessage(int index, int opcode){
        this.index = index;
        this.opcode = opcode;
    }

    public int getIndex(){
        return index;
    }

    public int getOpcode(){
        return opcode;
    }

    public int getUid(){
        return uid;
    }

    public T getData(){
        return data;
    }
}
