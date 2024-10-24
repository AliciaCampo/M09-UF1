package iticbcn.xifratge;
import java.util.*; 
public class XifradorMonoalfabetic {
    public static final char[] DICCIONARIO_MIN = "aáàbcdçeéèfghiíìjklmnñoóòpqrstuúùüvwxyz".toCharArray();
    public static final char[] DICCIONARIO_MAX = "AÁÀBCDÇEÉÈFGHIÍÌJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    public  static char [] permutaAlfabet(char [] alfabet) {
        ArrayList<Character> lista =  new ArrayList<Character>();
        for (char c : alfabet){
            lista.add(c);
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

    public static  String xifraMonoAlfa(String texto , char [] alfabetoMin,char [] alfabetoMax ){
        StringBuilder cifrado = new StringBuilder();
        for (char c :texto.toCharArray()){
            if (Character.isLowerCase(c)) {
                int index = buscarIndice(c, DICCIONARIO_MIN);
                cifrado.append(index != -1 ? alfabetoMin[index] : c);
                
            } else if(Character.isUpperCase(c)) {
                int index = buscarIndice(c, DICCIONARIO_MAX);
                cifrado.append(index != -1 ? alfabetoMax[index] : c);
                
            }else{
                cifrado.append(c);
            }
        }
        return cifrado.toString();
    }
    public static  String desxifraMonoAlfa(String texto,char [] alfabetoMin,char [] alfabetoMax){
        StringBuilder descifrado = new StringBuilder();
        for (char c :texto.toCharArray()){
            if (Character.isLowerCase(c)) {
                int index = buscarIndice(c, alfabetoMin);
                descifrado.append(index != -1 ? DICCIONARIO_MIN[index] : c);
                
            } else if(Character.isUpperCase(c)) {
                int index = buscarIndice(c, alfabetoMax);
                descifrado.append(index != -1 ? DICCIONARIO_MAX[index] : c);
                
            }else{
                descifrado.append(c);
            }
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
    
}