package message;

import com.alibaba.fastjson.JSONArray;
import logger.JLogger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

/**
 * 默认
 */
public class ReceiveMessageImp<String> extends ReceiveMessage<String>{
    //方法名字
    String funName;
    //方法参数
    JSONArray funParams;


    public ReceiveMessageImp(int index, int opcode, IoSession ioSession, IoBuffer ioBuffer) {
        super(index, opcode, ioSession, ioBuffer);
    }

    @Override
    public void getRemain() throws CharacterCodingException {
        try {
            uid = remain.getInt();
            data = (String) remain.getPrefixedString(4, Charset.forName("UTF-8").newDecoder());
            //转化成一个Jsonarray
            JSONArray array = JSONArray.parseArray((java.lang.String) data);
            funName = (String) array.getString(0);
            funParams = array.getJSONArray(1);
        }catch (Exception e){
             JLogger.error("DefaultReceiveMessage getRemain error", e);
        }
    }

    public String getFunName() {
        return funName;
    }

    public JSONArray getFunParams() {
        return funParams;
    }

    public IoSession getIosession(){
        return ioSession;
    }
}
