package code;

import org.xerial.snappy.Snappy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

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
