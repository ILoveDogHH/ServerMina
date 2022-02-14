package code;


import message.SendMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ServerEncode extends ProtocolEncoderAdapter {

    //加密文件
    Encry encry;

    //压缩文件
    Compression compression;


    public ServerEncode(){
        this.encry = new EncryImp();
        this.compression = new CompressionImp();
    }

    /**
     * 加密
      * @param session
     * @param message
     * @param out
     * @throws Exception
     */
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        SendMessage<?> sendMessage = (SendMessage<?>) message;
        IoBuffer in = sendMessage.toArray();
        int size = in.remaining();
        byte[] bytes = new byte[size];
        in.get(bytes);
        //压缩
        if(compression != null && compression.isCompression()){
            bytes = compression.compression(bytes);
        }
        //加密
        if(encry != null && encry.isEncry()){
            bytes = encry.encry(bytes);
        }
        //获取到解密文件
//        IoBuffer buf = IoBuffer.allocate(100);
//        buf.setAutoExpand(true);
//        buf.put(bytes);
//        buf.flip();
        IoBuffer buf = IoBuffer.wrap(bytes);
        out.write(buf);
    }
}
