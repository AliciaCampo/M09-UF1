import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
public class Hashes {
    public int npass = 0;
    public static final char[] abc = "abcdefABCDEF1234567890!".toCharArray();
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            // Crear una instancia de MessageDigest con el algoritmo SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            // Convertir la contraseña y el salto en bytes y calcular el hash
            byte[] hashBytes = digest.digest((pw + salt).getBytes());
            // Usar HexFormat para convertir el array de bytes en un string hexadecimal
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el hash SHA-512", e);
        }
    }
    public String getPBKDF2AmbSalt(String pw, String salt){
        try {
            int interaciones = 6536;
            int KeyLength = 512;
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), interaciones, KeyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashBytes = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el hash PBKDF2", e);
        }
    }
    public String forcaBruta(String alg, String hash, String salt){

    }
    public String getInterval(long t1, long t2){
        long milis = t2 - t1;
        long seconds = milis / 1000;
        long minutes = seconds / 60;
        seconds = seconds % 60;
        long hours = minutes / 60;
        minutes = minutes % 60;
        return String.format("%d:%02d:%02d", hours, minutes, seconds);
    }
    public String getSHA512(String pw){
        try {
            // Crear una instancia de MessageDigest con el algoritmo SHA-512
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            // Convertir la contraseña en bytes y calcular el hash
            byte[] hashBytes = digest.digest(pw.getBytes());
            // Usar HexFormat para convertir el array de bytes en un string hexadecimal
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el hash SHA-512", e);
        }
    }

    }
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();
            
            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
        }

}