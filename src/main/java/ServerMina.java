
import alive.KeepAliveImp;
import code.ServerDecode;
import code.ServerEncode;
import handler.MinaServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

public class ServerMina {



    private static final int PORT = 8888;
    /**
     *
     */
    public ServerMina() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 服务器端的主要对象
        IoAcceptor acceptor = new NioSocketAcceptor();

        // 设置Filter链
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        // 协议解析，采用mina现成的UTF-8字符串处理方式

        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ServerEncode(), new ServerDecode()));
//        //启动默认线程池
       acceptor.getFilterChain().addLast("executor", new ExecutorFilter());

       acceptor.getFilterChain().addLast("alive", KeepAliveImp.getKeepAliveFilter(5, 30));
        // 设置消息处理类（创建、关闭Session，可读可写等等，继承自接口IoHandler）
        acceptor.setHandler(new MinaServerHandler());
        // 设置接收缓存区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        try {
            // 服务器开始监听
            acceptor.bind( new InetSocketAddress(PORT) );
            System.out.println("服务器启动");
        }catch(Exception e){
            e.printStackTrace();
        }
    }





}
