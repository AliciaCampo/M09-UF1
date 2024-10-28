package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
public class XifradorMonoalfabetic implements Xifrador {
    public static final char[] DICCIONARIO_MIN = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    public static final char[] DICCIONARIO_MAX = "AÁÀBCDÇEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] alfabetoMin;
    private char[] alfabetoMax;
    public XifradorMonoalfabetic() {
        this.alfabetoMin = permutaAlfabet(DICCIONARIO_MIN);
        this.alfabetoMax = permutaAlfabet(DICCIONARIO_MAX);
    }
    private char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> lista = new ArrayList<>();
        for (char c : alfabet) {
            lista.add(c);
        }
        Collections.shuffle(lista);
        char[] arrayPermutado = new char[alfabet.length];
        for (int a = 0; a < lista.size(); a++) {
            arrayPermutado[a] = lista.get(a);
        }
        return arrayPermutado;
    }
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String textoCifrado = xifraMonoAlfa(msg, alfabetoMin, alfabetoMax);
        return new TextXifrat(textoCifrado.getBytes());
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
        String texto = new String(xifrat.getBytes());
        return desxifraMonoAlfa(texto, alfabetoMin, alfabetoMax);
    }
    private String xifraMonoAlfa(String texto, char[] alfabetoMin, char[] alfabetoMax) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLowerCase(c)) {
                int index = buscarIndice(c, DICCIONARIO_MIN);
                cifrado.append(index != -1 ? alfabetoMin[index] : c);
            } else if (Character.isUpperCase(c)) {
                int index = buscarIndice(c, DICCIONARIO_MAX);
                cifrado.append(index != -1 ? alfabetoMax[index] : c);
            } else {
                cifrado.append(c);
            }
        }
        return cifrado.toString();
    }
    private String desxifraMonoAlfa(String texto, char[] alfabetoMin, char[] alfabetoMax) {
        StringBuilder descifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (Character.isLowerCase(c)) {
                int index = buscarIndice(c, alfabetoMin);
                descifrado.append(index != -1 ? DICCIONARIO_MIN[index] : c);
            } else if (Character.isUpperCase(c)) {
                int index = buscarIndice(c, alfabetoMax);
                descifrado.append(index != -1 ? DICCIONARIO_MAX[index] : c);
            } else {
                descifrado.append(c);
            }
        }
        return descifrado.toString();
    }
    private int buscarIndice(char letra, char[] diccionario) {
        for (int i = 0; i < diccionario.length; i++) {
            if (diccionario[i] == letra) {
                return i;
            }
        }
        return -1;
    }
}