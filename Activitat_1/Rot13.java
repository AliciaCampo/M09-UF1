package Activitat_1;
public class Rot13 {
    public static final char[] DICCIONARIO_MIN = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', 'á', 'à' , 'é', 'è' , 'í', 'ì', 'ó', 'ò', 'ú', 'ù', 'ñ', 'ç'
    };
    public static final char[] DICCIONARIO_MAX = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'Á', 'À','È', 'É', 'Í','Ì', 'Ó', 'Ò', 'Ú', 'Ù' , 'Ñ', 'Ç'
    };
    public static void main(String[] args) {
        String entrada = "El conocimiento es la llave del éxito.";
        System.out.println("Texto a cifrar: " + entrada);
        String resultadoCifrado = xifraRot13(entrada);
        System.out.println("El mensaje '" + entrada + "' se ha cifrado a: " + resultadoCifrado);
        String resultadoDescifrado = desxifraRot13(resultadoCifrado);
        System.out.println("El mensaje cifrado '" + resultadoCifrado + "' se ha descifrado a: " + resultadoDescifrado);
    }
    public static String xifraRot13(String mensaje) {
        String codificado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                codificado += desplazarRot13(letra, DICCIONARIO_MIN, 13);
            } 
            else if (Character.isUpperCase(letra)) {
                codificado += desplazarRot13(letra, DICCIONARIO_MAX, 13);
            } 
            else {
                codificado += letra;
            }
        }
        
        return codificado;
    }
    public static String desxifraRot13(String mensaje) {
        String descodificado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                descodificado += desplazarRot13(letra, DICCIONARIO_MIN, -13);
            } 
            else if (Character.isUpperCase(letra)) {
                descodificado += desplazarRot13(letra, DICCIONARIO_MAX, -13);
            } 
            else {
                descodificado += letra;
            }
        }
        return descodificado;
    }
    public static char desplazarRot13(char letra, char[] diccionario, int desplazamiento) {
        for (int i = 0; i < diccionario.length; i++) {
            if (diccionario[i] == letra) {
                int nuevaPos = (i + desplazamiento) % diccionario.length;
                if (nuevaPos < 0) {
                    nuevaPos += diccionario.length;
                }
                return diccionario[nuevaPos];
            }
        }
        return letra;
    }
}