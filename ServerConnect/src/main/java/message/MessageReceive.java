package message;

import java.nio.charset.CharacterCodingException;

/**
 * 接收消息
 */
public interface MessageReceive<T> {
    //获取到剩余数据
    void getRemain() throws CharacterCodingException;
}
