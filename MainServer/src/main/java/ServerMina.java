package main.java;

import alive.KeepAliveImp;
import code.ServerDecode;
import code.ServerEncode;
import dao.UserDao;
import entity.CfgAct112;
import handler.MinaServerHandler;
import logger.JLogger;
import manager.DatabaseManager;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import utils.ConfigCenter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.List;

public class ServerMina {



    private static final int PORT = 8888;
    /**
     *
     */


    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //加载配置文件
        ConfigCenter.reloadConfig();
        System.out.println("加载配置文件成功");

        //注册数据库
        DatabaseManager.register();

        createSocket();
        JLogger.debug("服务器启动成功");
        System.out.println("success");

        test();
    }


    private static void test() throws SQLException {
        List<CfgAct112> data = UserDao.getInstance().getListData(CfgAct112.class, "select * from cfg_act112");
        System.out.println(data.toString());

    }


    private static void createSocket(){

        // 服务器端的主要对象
        IoAcceptor acceptor = new NioSocketAcceptor();

        // 设置Filter链
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        // 协议解析，采用mina现成的UTF-8字符串处理方式

        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ServerEncode(), new ServerDecode()));
//        //启动默认线程池
        acceptor.getFilterChain().addLast("executor", new ExecutorFilter());

        acceptor.getFilterChain().addLast("alive", KeepAliveImp.getKeepAliveFilter(60, 30));
        // 设置消息处理类（创建、关闭Session，可读可写等等，继承自接口IoHandler）
        acceptor.setHandler(new MinaServerHandler());
        // 设置接收缓存区大小
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        try {
            // 服务器开始监听
            acceptor.bind( new InetSocketAddress(PORT) );
        }catch(Exception e){
            e.printStackTrace();
        }


    }





}
