package code;

public class NoCompressionImp implements NoCompression{
    @Override
    public byte[] noCompression(byte[] bytes) {
        return new byte[0];
    }

    @Override
    public boolean isNoCompression() {
        return false;
    }
}
