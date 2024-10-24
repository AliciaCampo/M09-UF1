package iticbcn.xifratge;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "1234567890123456";
    public  String desxifra(byte[] bIvIMsgXifrat, String clau) throws Exception {// Método para descifrar un mensaje con AES
        Cipher cipher = Cipher.getInstance(FORMAT_AES);// Obtener una instancia del cifrador AES en modo CBC con padding PKCS5
        SecretKeySpec key = new SecretKeySpec(clau.getBytes(), ALGORISME_XIFRAT);// Crear la clave secreta a partir de la cadena "clau"
        IvParameterSpec ivspec = new IvParameterSpec(bIvIMsgXifrat, 0, MIDA_IV);// Extraer el IV de los primeros 16 bytes del array bIvIMsgXifrat
        cipher.init(Cipher.DECRYPT_MODE, key, ivspec);// Inicializar el cifrador en modo descifrado con la clave y el IV
        byte[] msg = cipher.doFinal(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length - MIDA_IV);// Descifrar el mensaje, saltándose los primeros 16 bytes (que corresponden al IV)
        return new String(msg, "UTF-8");// Convertir el mensaje descifrado (array de bytes) en una cadena de texto
    }
    public  byte[] xifraAES(String msg, String clau) throws Exception {// Método para cifrar un mensaje con AES
        Cipher cipher = Cipher.getInstance(FORMAT_AES);// Obtener una instancia del cifrador AES en modo CBC con padding PKCS5
        SecretKeySpec key = new SecretKeySpec(clau.getBytes(), ALGORISME_XIFRAT);// Crear la clave secreta a partir de la cadena "clau"
        IvParameterSpec ivspec = new IvParameterSpec(iv);// Crear un IvParameterSpec usando el IV predefinido
        cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);// Inicializar el cifrador en modo cifrado con la clave y el IV
        byte[] msgEnc = cipher.doFinal(msg.getBytes());// Cifrar el mensaje (convertido a bytes)
        byte[] bIvIMsgXifrat = new byte[MIDA_IV + msgEnc.length];// Crear un array que contendrá el IV seguido del mensaje cifrado
        System.arraycopy(iv, 0, bIvIMsgXifrat, 0, MIDA_IV);// Copiar el IV al inicio del array bIvIMsgXifrat
        System.arraycopy(msgEnc, 0, bIvIMsgXifrat, MIDA_IV, msgEnc.length);// Copiar el mensaje cifrado después del IV en el array bIvIMsgXifrat
        return bIvIMsgXifrat;// Devolver el array que contiene el IV y el mensaje cifrado
    }   
}