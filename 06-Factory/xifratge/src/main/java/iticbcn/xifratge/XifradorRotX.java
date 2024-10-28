package iticbcn.xifratge;
public class XifradorRotX implements Xifrador {
    public static final char[] DICCIONARIO_MIN = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    public static final char[] DICCIONARIO_MAX = "AÁÀBCDÇEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private int rotacion;
    // Constructor vacío
    public XifradorRotX() {}
    private char desplazarRot13(char letra, char[] diccionario, int desplazamiento) {
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
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        // Inicializamos la rotación con la clave dada
        inicializaRotacion(clau);
        String mensajeCifrado = xifraRotX(msg, rotacion);
        return new TextXifrat(mensajeCifrado.getBytes());
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        // Inicializamos la rotación con la clave dada
        inicializaRotacion(clau);
        String mensajeDescifrado = desxifraRotX(new String(xifrat.getBytes()), rotacion);
        return mensajeDescifrado;
    }
    private void inicializaRotacion(String clau) throws ClauNoSuportada {
        try {
            rotacion = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
        // Verifica que la rotación esté entre 0 y 40
        if (rotacion < 0 || rotacion > 40) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }
    // Método para cifrar usando RotX con la rotación dada
    private String xifraRotX(String mensaje, int rotacion) {
        StringBuilder codificado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                codificado.append(desplazarRot13(letra, DICCIONARIO_MIN, rotacion));
            } else if (Character.isUpperCase(letra)) {
                codificado.append(desplazarRot13(letra, DICCIONARIO_MAX, rotacion));
            } else {
                codificado.append(letra);
            }
        }
        return codificado.toString();
    }
    // Método para descifrar usando RotX con la rotación dada
    private String desxifraRotX(String mensaje, int rotacion) {
        StringBuilder descodificado = new StringBuilder();
        for (int i = 0; i < mensaje.length(); i++) {
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)) {
                descodificado.append(desplazarRot13(letra, DICCIONARIO_MIN, -rotacion));
            } else if (Character.isUpperCase(letra)) {
                descodificado.append(desplazarRot13(letra, DICCIONARIO_MAX, -rotacion));
            } else {
                descodificado.append(letra);
            }
        }
        return descodificado.toString();
    }
}