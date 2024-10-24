package iticbcn.xifratge;
import java.util.*; 
public class XifradorPolialfabetic {
    public static final char[] DICCIONARIO = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyzAÁÀBCDÇEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();    
    public static  int clauSecreta;
    public static Random random = new Random();
    public  static char [] permutaAlfabet(char [] alfabet) {
        ArrayList<Character> lista =  new ArrayList<Character>();
        for (char c : alfabet){
            lista.add(c);
        }
        //para usar el Shuffling 
        Collections.shuffle(lista,random);
        char [] arrayPermutado = new char [alfabet.length];
        //pasamos el alfabeto permutadoal nuevo array de char
        for ( int a = 0 ; a < lista.size() ; a++){
            arrayPermutado[a] = lista.get(a);
        }
       return arrayPermutado;
    }

    public static String xifraPoliAlfa(String texto) {
        StringBuilder cifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            char[] alfabetoPermutado = permutaAlfabet(DICCIONARIO);
            int index = buscarIndice(c, DICCIONARIO);
            cifrado.append(index != -1 ? alfabetoPermutado[index] : c);
        }
        return cifrado.toString();
    }
    public static String desxifraPoliAlfa(String texto) {
        StringBuilder descifrado = new StringBuilder();
        for (char c : texto.toCharArray()) {
            char[] alfabetoPermutado = permutaAlfabet(DICCIONARIO);
            int index = buscarIndice(c, alfabetoPermutado);
            descifrado.append(index != -1 ? DICCIONARIO[index] : c);
        }
        return descifrado.toString();
    }
    public static  int  buscarIndice (char letra , char[] diccionario){
        //con la posición dice que letra es
        for (int i = 0 ; i < diccionario.length;i++){
            if( diccionario[i]== letra){
                return i;
            }
        }
        return -1 ;
    }
    public static void initRandom(int clau){
        random.setSeed(clau);
    }
    
}