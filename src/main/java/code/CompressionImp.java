package code;

import org.xerial.snappy.Snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

public class CompressionImp implements Compression{
    /**
     * 压缩效率不高压缩速度较快
     * @param bytes
     * @return
     * @throws IOException
     */
    @Override
    public byte[] compression(byte[] bytes) throws IOException {
        return  Snappy.compress(bytes);
    }

    @Override
    public boolean isCompression() {
        return true;
    }
}
