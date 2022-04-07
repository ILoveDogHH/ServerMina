package handler;

import com.alibaba.fastjson.JSONArray;
import message.AbstractMessage;

public class VoidHandler extends HandlerAdapter{
    public VoidHandler(String pack, Class<?>... params) {
        super(pack, params);
    }

    @Override
    public JSONArray execute(AbstractMessage<?> messageReceived) {
        return new JSONArray();
    }
}
