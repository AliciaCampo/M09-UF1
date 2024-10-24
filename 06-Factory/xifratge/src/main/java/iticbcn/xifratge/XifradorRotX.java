package iticbcn.xifratge;
//programa basado en el Rot13
public class XifradorRotX {
    public static final char[] DICCIONARIO_MIN = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    public static final char[] DICCIONARIO_MAX = "AÁÀBCDÇEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();


    public static String xifraRotX(String mensaje, int rotacion) {
        StringBuilder codificado = new StringBuilder(); 
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                codificado.append(desplazarRot13(letra, DICCIONARIO_MIN, rotacion)); 
            }
            else if (Character.isUpperCase(letra)) {
                codificado.append(desplazarRot13(letra, DICCIONARIO_MAX, rotacion)); 
            }
            else {
                codificado.append(letra); 
            }
        }
        return codificado.toString(); 
    }
    public static String desxifraRotX(String mensaje , int rotacion) {
        StringBuilder descodificado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                descodificado.append(desplazarRot13(letra, DICCIONARIO_MIN, -rotacion));
            } 
            else if (Character.isUpperCase(letra)) {
                descodificado.append(desplazarRot13(letra, DICCIONARIO_MAX, -rotacion));
            } 
            else {
                descodificado.append(letra);
            }
        }
        return descodificado.toString();
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