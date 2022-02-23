package code;

public enum OpcodeEnum {

    UnknownMessage(-1,"未知"),

    HeartSendMessage(1, "心跳检测发送消息"),

    HeartReceiveMessage(2, "心跳检测接受消息"),

    ReceiveMessage(1001, "接受消息"),

    SendMessage(2001, "发送消息"),

    ResponseMessage(3001, "响应消息");


    public int opcode;

    public String msg;

    OpcodeEnum(int opcode, String msg){
        this.opcode = opcode;
        this.msg = msg;
    }

    public static OpcodeEnum getEnum(int opcode){
        for(OpcodeEnum value : values()){
            if(value.opcode == opcode){
                return value;
            }
        }
        return UnknownMessage;
    }
}
