package Activitat_1;
//programa basado en el Rot13
public class RotX {
    public static final char[] DICCIONARIO_MIN = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    public static final char[] DICCIONARIO_MAX = "AÁÀBCDÇEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    public static void main(String[] args) {
        int rotacion = 10 ;
        String entrada = "El conocimiento es la llave del éxito.";
        System.out.println("Texto a cifrar: " + entrada);
        System.out.println("Rotación : " + rotacion);
        String resultadoCifrado = xifraRotX(entrada , rotacion);
        System.out.println("El mensaje '" + entrada + "' se ha cifrado a: " + resultadoCifrado);
        String resultadoDescifrado = desxifraRotX(resultadoCifrado , rotacion);
        System.out.println("El mensaje cifrado '" + resultadoCifrado + "' se ha descifrado a: " + resultadoDescifrado);
        System.out.println();
        System.err.println("Si queremos descifrar el mensaje sin sabre la rotación ");
        System.out.println(forcaBrutaRotX(resultadoCifrado));
    }
    public static String xifraRotX(String mensaje , int rotacion) {
        String codificado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                codificado += desplazarRot13(letra, DICCIONARIO_MIN, rotacion);
            } 
            else if (Character.isUpperCase(letra)) {
                codificado += desplazarRot13(letra, DICCIONARIO_MAX, rotacion);
            } 
            else {
                codificado += letra;
            }
        }
        return codificado;
    }
    public static String desxifraRotX(String mensaje , int rotacion) {
        String descodificado = "";
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                descodificado += desplazarRot13(letra, DICCIONARIO_MIN, -rotacion);
            } 
            else if (Character.isUpperCase(letra)) {
                descodificado += desplazarRot13(letra, DICCIONARIO_MAX, -rotacion);
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
    public static String forcaBrutaRotX(String mensajeCifrado) {
        StringBuilder resultado = new StringBuilder(); 
        int longitudAlfabeto = DICCIONARIO_MAX.length; 
        for (int i = 0; i <= longitudAlfabeto; i++) {
            String textoDescifrado = desxifraRotX(mensajeCifrado, i); 
            resultado.append("Desplazamiento: ").append(i).append(", Texto descifrado: ").append(textoDescifrado).append("\n");
        }
        return resultado.toString();
    }
    
}