package handler;

import cmdReturn.CmdReturn;
import com.alibaba.fastjson.JSONArray;
import exception.DefaultException;
import message.AbstractMessage;

public class ErrorHandler extends HandlerAdapter{
    public ErrorHandler(String pack) {
        super(pack);
    }

    @Override
    public JSONArray execute(AbstractMessage<?> messageReceived) {
        String msg = String.format("Undefined opcode %d", messageReceived.getOpcode());
        Exception e = new DefaultException(msg);
        return CmdReturn.error(e.getLocalizedMessage());
    }
}
