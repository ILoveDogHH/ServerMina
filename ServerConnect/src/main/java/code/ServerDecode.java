package code;

import message.MessageAdapter;
import message.MessageAdapterImp;
import message.MessageReceive;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ServerDecode extends CumulativeProtocolDecoder {

    /**
     * 解密
     */
    private Decrypt decry;

    /**
     * 解压
     */
    private NoCompression noCompression;

    /**
     * 接收消息是适配器
     */
    private MessageAdapter<?> adapter;


    public ServerDecode(){
        this.decry = new DecryImp();
        this.noCompression = new NoCompressionImp();
        this.adapter = new MessageAdapterImp<>();
    }

    /**
     * 解码。解压
     * @param session
     * @param in
     * @param out
     * @return
     * @throws Exception
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {

        int size = in.remaining();
        if(size < 4){
            return false;
        }
        byte[] bytes = new byte[size];
        in.get(bytes);

        //解密
        if(decry != null && decry.isDecry()){
            bytes = decry.decrypt(bytes);
        }

        //解压
        if(noCompression != null && noCompression.isNoCompression()){
            bytes = noCompression.noCompression(bytes);
        }

        IoBuffer buffer = IoBuffer.wrap(bytes);
        //通过opcode获取到解码器
        MessageReceive<?> messageReceive = adapter.getMessage(session, buffer);
        messageReceive.getRemain();
        out.write(messageReceive);
        return true;
    }
}
