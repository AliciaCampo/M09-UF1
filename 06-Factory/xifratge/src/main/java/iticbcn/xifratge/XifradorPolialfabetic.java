package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class XifradorPolialfabetic implements Xifrador {
    public static final char[] DICCIONARIO = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyzAÁÀBCDÇEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private int clauSecreta;
    private Random random = new Random();
    public XifradorPolialfabetic() {
        // Constructor vacío, pero necesario para inicializar instancias.
    }
    private char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> lista = new ArrayList<>();
        for (char c : alfabet) {
            lista.add(c);
        }
        Collections.shuffle(lista, random);
        char[] arrayPermutado = new char[alfabet.length];
        for (int a = 0; a < lista.size(); a++) {
            arrayPermutado[a] = lista.get(a);
        }
        return arrayPermutado;
    }
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            inicializaRandom(clau);
        } catch (ClauNoSuportada e) {
            System.out.println("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
            return null;
        }
        String textoCifrado = xifraPoliAlfa(msg);
        return new TextXifrat(textoCifrado.getBytes());
    }
    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try{
            inicializaRandom(clau);

        }catch(ClauNoSuportada e){
            System.out.println("La clau de Polialfabètic ha de ser un String convertible a long");
            return null;
        }
        String texto = new String(xifrat.getBytes());
        return desxifraPoliAlfa(texto);
    }
    private void inicializaRandom(String clau) throws ClauNoSuportada {
        try {
            clauSecreta = Integer.parseInt(clau);
            random.setSeed(clauSecreta);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau no suportada: la clau ha de ser un enter.");
        }
    }
    private String xifraPoliAlfa(String texto) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            char[] alfabetoPermutado = permutaAlfabet(DICCIONARIO);
            int index = buscarIndice(c, DICCIONARIO);
            cifrado.append(index != -1 ? alfabetoPermutado[index] : c);
        }
        return cifrado.toString();
    }
    private String desxifraPoliAlfa(String texto) {
        StringBuilder descifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            char[] alfabetoPermutado = permutaAlfabet(DICCIONARIO);
            int index = buscarIndice(c, alfabetoPermutado);
            descifrado.append(index != -1 ? DICCIONARIO[index] : c);
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