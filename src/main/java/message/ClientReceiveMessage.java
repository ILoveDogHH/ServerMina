package message;

import com.alibaba.fastjson.JSONArray;
import logger.JLogger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class ClientReceiveMessage<String> extends ReceiveMessage<String> {

    public ClientReceiveMessage(int index, int opcode, IoSession ioSession, IoBuffer ioBuffer) {
        super(index, opcode, ioSession, ioBuffer);
    }

    @Override
    public void getRemain() throws CharacterCodingException {
        try {
            uid = remain.getInt();
            data = (String) remain.getPrefixedString(4, Charset.forName("UTF-8").newDecoder());
        }catch (Exception e){
            JLogger.error("DefaultReceiveMessage getRemain error", e);
        }
    }
}
