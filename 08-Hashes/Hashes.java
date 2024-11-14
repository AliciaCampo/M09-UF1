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
            int interaciones = 100;
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
    public String forcaBruta(String alg, String targetHash, String salt) {
        npass = 0; // Reiniciar contador de contraseñas probadas
        // Intentar combinaciones de longitud de 1 a 6 caracteres
        for (int len = 1; len <= 6; len++) {
            char[] attempt = new char[len];

            // Bucles anidados para probar cada combinación de longitud `len`
            for (int i = 0; i < abc.length; i++) {
                attempt[0] = abc[i];
                if (len > 1) {
                    for (int j = 0; j < abc.length; j++) {
                        attempt[1] = abc[j];
                        if (len > 2) {
                            for (int k = 0; k < abc.length; k++) {
                                attempt[2] = abc[k];
                                if (len > 3) {
                                    for (int l = 0; l < abc.length; l++) {
                                        attempt[3] = abc[l];
                                        if (len > 4) {
                                            for (int m = 0; m < abc.length; m++) {
                                                attempt[4] = abc[m];
                                                if (len > 5) {
                                                    for (int n = 0; n < abc.length; n++) {
                                                        attempt[5] = abc[n];
                                                        npass++;
                                                        if (testPassword(new String(attempt), alg, targetHash, salt)) {
                                                            return new String(attempt);
                                                        }
                                                    }
                                                } else {
                                                    npass++;
                                                    if (testPassword(new String(attempt), alg, targetHash, salt)) {
                                                        return new String(attempt);
                                                    }
                                                }
                                            }
                                        } else {
                                            npass++;
                                            if (testPassword(new String(attempt), alg, targetHash, salt)) {
                                                return new String(attempt);
                                            }
                                        }
                                    }
                                } else {
                                    npass++;
                                    if (testPassword(new String(attempt), alg, targetHash, salt)) {
                                        return new String(attempt);
                                    }
                                }
                            }
                        } else {
                            npass++;
                            if (testPassword(new String(attempt), alg, targetHash, salt)) {
                                return new String(attempt);
                            }
                        }
                    }
                } else {
                    npass++;
                    if (testPassword(new String(attempt), alg, targetHash, salt)) {
                        return new String(attempt);
                    }
                }
            }
        }
        return null; // No se encontró la contraseña
    }
    public boolean testPassword(String attempt, String alg, String targetHash, String salt) {
        String generatedHash = alg.equals("SHA-512") ? getSHA512AmbSalt(attempt, salt) : getPBKDF2AmbSalt(attempt, salt);
        return generatedHash.equals(targetHash);
    }

    public String getInterval(long t1, long t2) {
        long millis = t2 - t1;
        long segundos = millis / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;
        millis %= 1000;
        segundos %= 60;
        minutos %= 60;
        horas %= 24;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dias, horas, minutos, segundos, millis);
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