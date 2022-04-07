package code;

import org.xerial.snappy.Snappy;

import java.io.IOException;

public class NoCompressionImp implements NoCompression{

    @Override
    public byte[] noCompression(byte[] bytes) throws IOException {
        return Snappy.uncompress(bytes);
    }

    @Override
    public boolean isNoCompression() {
        return true;
    }
}
