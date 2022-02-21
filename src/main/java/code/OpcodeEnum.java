package code;

public enum OpcodeEnum {

    UnknownMessage(-1,"未知"),

    KeepSendMessage(1, "服务器发送心跳检测信息"),

    KeepReceiveMessage(2, "服务器接受心跳检测信息"),

    DefaultReceiveMessage(1001, "服务器接收客户端消息"),

    DefaultSendMessage(2001, "服务器响应客户端消息");


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
