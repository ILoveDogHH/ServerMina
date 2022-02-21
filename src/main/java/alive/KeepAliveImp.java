package alive;

import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class KeepAliveImp {
    //10秒钟心跳检测一次
    private static final int IDEA = 10;

    //5秒请求超时则断线
    private static final int TIME_OUT = 5;

    public static KeepAliveFilter getKeepAliveFilter(){
        KeepAliveFilter filter = new KeepAliveFilter(new KeepAliveMessageFactoryImp());
        filter.setRequestInterval(IDEA);
        filter.setRequestTimeout(TIME_OUT);
        return filter;
    }









}
