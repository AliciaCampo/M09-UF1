package iticbcn.xifratge;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    // Método para generar un par de claves RSA
    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator claves = KeyPairGenerator.getInstance("RSA");
        claves.initialize(2048); // Tamaño seguro de clave en bits
        return claves.generateKeyPair();
    }
    // Método para cifrar un mensaje usando la clave pública
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cifrado = Cipher.getInstance("RSA");
        cifrado.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cifrado.doFinal(msg.getBytes());
    }
    // Método para descifrar un mensaje cifrado usando la clave privada
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher descifrado = Cipher.getInstance("RSA");
        descifrado.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] mensajeDescifrado = descifrado.doFinal(msgXifrat);
        return new String(mensajeDescifrado);
    }
}