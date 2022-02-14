package code;

public interface Compression {
    /**
     * 压缩
     * @param bytes
     * @return
     */
    byte[] compression(byte[] bytes);

    /**
     * 是否压缩
     * @return
     */
    boolean isCompression();
}
