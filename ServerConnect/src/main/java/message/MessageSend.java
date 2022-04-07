package message;

import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;

/**
 * 消息发送
 */
public interface MessageSend {
    IoBuffer toArray() throws CharacterCodingException;
}
