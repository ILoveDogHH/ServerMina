## **项目背景** ##
    Mina 主要是对基于 TCP/IP、UDP/IP 协议栈的通信框架，Mina 可以帮助我们快速开发高性能、高扩展性的网络通信应用，Mina 提供了事件驱动、异步操作的编程模型，Mina 的异步 IO 默认使用的是 JAVA NIO（New IO）作为底层支持，基于 Channel 的双向通道。
   

**项目简介**
----
该项目实现了服务器通讯，数据库连接和加密解密处理。可以用来做游戏服务器进行游戏开发业务内容。


## **功能说明** ##
核心代码：
    创建socket连接并且自定义Encrypt（加密），Compression（压缩）,KeepAliveImp(心跳检测).

        private static void createSocket(){

        // 服务器端的主要对象
        IoAcceptor acceptor = new NioSocketAcceptor();

        // 设置Filter链
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        // 协议解析，采用mina现成的UTF-8字符串处理方式

        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ServerEncode(),           new ServerDecode()));
        //启动默认线程池
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
    
    
自定义message结构体，发送和接受结构

    public abstract class  AbstractMessage<T> implements Message {

    int index;

    int opcode;

    int uid;

    T data;

    public AbstractMessage(int index, int opcode){
        this.index = index;
        this.opcode = opcode;
    }

    public int getIndex(){
        return index;
    }

    public int getOpcode(){
        return opcode;
    }

    public int getUid(){
        return uid;
    }

    public T getData(){
        return data;
    }
}

通过invoke方式进行业务层之间的调用

    JSONArray result = (JSONArray) method.invoke(null, uid, receiveMessage.getFunParams(), receiveMessage.getIosession());
    
   

        


    
    
    
    
    
