package handler;

import message.AbstractMessage;

public class ErrorHandler extends HandlerAdapter{
    public ErrorHandler(String pack) {
        super(pack);
    }

    @Override
    void execute(AbstractMessage<?> messageReceived) {

    }
}
