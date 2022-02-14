package code;

public enum OpcodeEnum {

    UnknownReceiveMessage(-1, "未知"),

    DefaultReceiveMessage(1001, "默认");

    int opcode;

    String msg;

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
        return UnknownReceiveMessage;
    }
}
