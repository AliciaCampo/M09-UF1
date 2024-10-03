import java.util.*; 
public class Monoalfabetic {
    public static final char[] DICCIONARIO_MIN = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    public char posicion (int posicion , char[] diccionario){
        //con la posición dice que letra es
        char letra ;
        for (int i = 0 ; i < diccionario.length;i++){
            if(posicion == diccionario[i]){
                letra = diccionario[i];
            }
        }
        return letra ;
    }
    public  char [] permutaAlfabet(char [] alfabet) {
        ArrayList<Character> lista =  new ArrayList<Character>();
        for (int i = 0 ; i < alfabet.length; i++){
            lista.add(alfabet[i]);
        }
        //para usar el Shuffling 
        Collections.shuffle(lista);
        char [] arrayPermutado = new char [alfabet.length];
        //pasamos el alfabeto permutadoal nuevo array de char
        for ( int a = 0 ; a < lista.size() ; a++){
            arrayPermutado[a] = lista.get(a);
        }
       return arrayPermutado;

    }

    public String xifraMonoAlfa(String texto){
        StringBuilder cifrado = new StringBuilder();
        return cifrado.toString();
    }
    public String desxifraMonoAlfa(String texto){
        StringBuilder descifrado = new StringBuilder();
        return descifrado.toString();
    }
}
