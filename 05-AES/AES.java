import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "1234567890123456";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", 
        "Hola Andrés cómo está tu cuñado", 
        "Àgora ïlla Ôtto"};
        
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i]; 
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " 
                + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKeySpec key = new SecretKeySpec(clau.getBytes(), ALGORISME_XIFRAT);
        IvParameterSpec ivspec = new IvParameterSpec(bIvIMsgXifrat, 0, MIDA_IV);
        cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
        byte[] msg = cipher.doFinal(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length - MIDA_IV);    
        return new String(msg,"UTF-8");
    }
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        SecretKeySpec key = new SecretKeySpec(clau.getBytes(), ALGORISME_XIFRAT);
        IvParameterSpec ivspec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
        byte[] msgEnc = cipher.doFinal(msg.getBytes());
        byte[] bIvIMsgXifrat = new byte[MIDA_IV + msgEnc.length];
        System.arraycopy(iv, 0, bIvIMsgXifrat, 0, MIDA_IV);
        System.arraycopy(msgEnc, 0, bIvIMsgXifrat, MIDA_IV, msgEnc.length);
        return bIvIMsgXifrat; // Devuelve el array de bytes (IV + mensaje cifrado)
        
    }   
}