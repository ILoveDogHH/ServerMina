package code;

public class CompressionImp implements Compression{
    @Override
    public byte[] compression(byte[] bytes) {
        return new byte[0];
    }

    @Override
    public boolean isCompression() {
        return false;
    }
}
