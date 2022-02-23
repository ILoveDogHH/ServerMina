package alive;

import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

public class KeepAliveImp {


    public static KeepAliveFilter getKeepAliveFilter(int IDEA, int TIME_OUT){
        KeepAliveFilter filter = new KeepAliveFilter(new KeepAliveMessageFactoryImp());
        filter.setRequestInterval(IDEA);
        filter.setRequestTimeout(TIME_OUT);
        return filter;
    }





}
