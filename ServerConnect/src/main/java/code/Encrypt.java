package code;

/**
 * 加密
 */
public interface Encrypt {

    byte[] encrypt(byte[] data) throws Exception;

    boolean isEncrypt();
}
