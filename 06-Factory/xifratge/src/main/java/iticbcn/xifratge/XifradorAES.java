package iticbcn.xifratge;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            // Ajustar la longitud de la clave a 16 caracteres
            String clauAjustada = ajustarLongitudClau(clau);
            SecretKeySpec key = new SecretKeySpec(clauAjustada.getBytes(), ALGORISME_XIFRAT);
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
            byte[] msgEnc = cipher.doFinal(msg.getBytes("UTF-8"));

            // Combinar IV y mensaje cifrado
            byte[] bIvIMsgXifrat = new byte[MIDA_IV + msgEnc.length];
            System.arraycopy(iv, 0, bIvIMsgXifrat, 0, MIDA_IV);
            System.arraycopy(msgEnc, 0, bIvIMsgXifrat, MIDA_IV, msgEnc.length);

            return new TextXifrat(bIvIMsgXifrat);

        } catch (Exception e) {
            System.err.println("Error en el cifrado AES: " + e.getMessage());
            return null;
        }
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            if (xifrat == null) {
                throw new ClauNoSuportada("TextXifrat es null. No se puede descifrar.");
            }
            String clauAjustada = ajustarLongitudClau(clau);
            SecretKeySpec key = new SecretKeySpec(clauAjustada.getBytes(), ALGORISME_XIFRAT);
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            IvParameterSpec ivspec = new IvParameterSpec(xifrat.getBytes(), 0, MIDA_IV);
            cipher.init(Cipher.DECRYPT_MODE, key, ivspec);

            byte[] msgDec = cipher.doFinal(xifrat.getBytes(), MIDA_IV, xifrat.getBytes().length - MIDA_IV);
            return new String(msgDec, "UTF-8");

        } catch (Exception e) {
            System.err.println("Error en el descifrado AES: " + e.getMessage());
            return null;
        }
    }
    private String ajustarLongitudClau(String clau) throws ClauNoSuportada {
        if (clau.length() < 16) {
            // Rellenar con espacios si la clave es demasiado corta
            return String.format("%-16s", clau).substring(0, 16);
        } else if (clau.length() > 16) {
            // Recortar si la clave es demasiado larga
            return clau.substring(0, 16);
        }
        return clau;
    }
}