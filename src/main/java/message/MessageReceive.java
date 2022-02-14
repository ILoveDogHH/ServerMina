package message;

import org.apache.mina.core.buffer.IoBuffer;

/**
 * 接收消息
 */
public interface MessageReceive {
    //获取到剩余数据
    void getRemain(IoBuffer ioBuffer);
}
