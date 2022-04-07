package code;

import java.io.IOException;

public interface NoCompression {


    byte[] noCompression(byte[] bytes) throws IOException;

    boolean isNoCompression();
}
