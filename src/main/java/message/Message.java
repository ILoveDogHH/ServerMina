package message;

public interface Message<T> {

    int getIndex();

    int getOpcode();

    int getUid();

    T getData();
}
