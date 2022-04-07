package code;

import java.io.IOException;

public interface Compression {
    /**
     * 压缩
     * @param bytes
     * @return
     */
    byte[] compression(byte[] bytes) throws IOException;

    /**
     * 是否压缩
     * @return
     */
    boolean isCompression();
}
