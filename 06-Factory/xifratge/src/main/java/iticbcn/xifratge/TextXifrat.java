package iticbcn.xifratge;
import java.util.Arrays;
public class TextXifrat {
private byte[] bytes;
@Override
public String toString() {
    return "TextXifrat [bytes=" + Arrays.toString(bytes) + "]";
}
public TextXifrat(byte[] bytes) {
    this.bytes = bytes;
}
public byte[] getBytes() {
    return bytes;
}

}