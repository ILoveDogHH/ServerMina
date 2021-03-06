
import code.ServerDecode;
import code.ServerEncode;
import com.alibaba.fastjson.JSONArray;
import handler.MinaClientHandler;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

public class ClientMina {


    public static void main(String []args)throws Exception{
        //Create TCP/IP connection
        NioSocketConnector connector = new NioSocketConnector();

        //创建接受数据的过滤器
        DefaultIoFilterChainBuilder chain = connector.getFilterChain();

        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ServerEncode(), new ServerDecode()));


        //客户端的消息处理器：一个SamplMinaServerHander对象
        connector.setHandler(new MinaClientHandler());


        //连接到服务器：
        ConnectFuture cf = connector.connect(new InetSocketAddress("localhost",8888));

        System.out.println("启动成功");
        //Wait for the connection attempt to be finished.
        cf.awaitUninterruptibly();
        JSONArray result = new JSONArray();
        JSONArray params = new JSONArray();
        params.add(51301536);
        result.add("userLogin");
        result.add(params);
        // cf.getSession().write(new SendMessageImp(1, OpcodeEnum.SendMessage.opcode,1,result.toJSONString()));
//        cf.getSession().getCloseFuture().awaitUninterruptibly();
//        connector.dispose();

    }




}
