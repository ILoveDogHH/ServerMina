import code.*;

import java.io.IOException;

public class test {


    public static void main(String[] args) throws IOException {
        String data = "这是一个车位水电费文我欠人情无热沃尔沃日而且让他我去二隔热个钱水电费文而且我我认为若群无若无群服务而为人我惹千万天气热   问题特二清热驱蚊分文未器翁人情味无未确认群无若无群若无群若千万人无群二去我去";
        Compression compression = new CompressionImp();
        NoCompression noCompression = new NoCompressionImp();
        byte[] bytes = data.getBytes();
        System.out.println("first " + bytes.length);
        bytes =  compression.compression(data.getBytes());
        System.out.println("compress " + bytes.length);

        bytes = noCompression.noCompression(bytes);
        System.out.println("unCompress " + bytes.length);
        System.out.println(new String(bytes));

    }



}
