import java.util.*; 
public class Monoalfabetic {
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
    public static void main(String[] args) {
        // Generamos los alfabetos permutados
        char[] alfabetoMinPermutado = permutaAlfabet(DICCIONARIO_MIN);
        char[] alfabetoMaxPermutado = permutaAlfabet(DICCIONARIO_MAX);
        // Prueba 1: Texto sencillo
        String textoOriginal1 = "Hola Mundo!";
        String textoCifrado1 = xifraMonoAlfa(textoOriginal1, alfabetoMinPermutado, alfabetoMaxPermutado);
        String textoDescifrado1 = desxifraMonoAlfa(textoCifrado1, alfabetoMinPermutado, alfabetoMaxPermutado);
        System.out.println("==== PRUEBA 1 ====");
        System.out.println("Texto original: " + textoOriginal1);
        System.out.println("Texto cifrado: " + textoCifrado1);
        System.out.println("Texto descifrado: " + textoDescifrado1);
        System.out.println();
        // Prueba 2: Texto con acentos y caracteres especiales
        String textoOriginal2 = "¡Buenos días! ¿Cómo estás?";
        String textoCifrado2 = xifraMonoAlfa(textoOriginal2, alfabetoMinPermutado, alfabetoMaxPermutado);
        String textoDescifrado2 = desxifraMonoAlfa(textoCifrado2, alfabetoMinPermutado, alfabetoMaxPermutado);
        System.out.println("==== PRUEBA 2 ====");
        System.out.println("Texto original: " + textoOriginal2);
        System.out.println("Texto cifrado: " + textoCifrado2);
        System.out.println("Texto descifrado: " + textoDescifrado2);
        System.out.println();
        // Prueba 3: Texto con solo minúsculas
        String textoOriginal3 = "el perro come pan";
        String textoCifrado3 = xifraMonoAlfa(textoOriginal3, alfabetoMinPermutado, alfabetoMaxPermutado);
        String textoDescifrado3 = desxifraMonoAlfa(textoCifrado3, alfabetoMinPermutado, alfabetoMaxPermutado);
        System.out.println("==== PRUEBA 3 ====");
        System.out.println("Texto original: " + textoOriginal3);
        System.out.println("Texto cifrado: " + textoCifrado3);
        System.out.println("Texto descifrado: " + textoDescifrado3);
        System.out.println();

        // Prueba 4: Texto con solo mayúsculas
        String textoOriginal4 = "EL GATO MIRA EL SOL";
        String textoCifrado4 = xifraMonoAlfa(textoOriginal4, alfabetoMinPermutado, alfabetoMaxPermutado);
        String textoDescifrado4 = desxifraMonoAlfa(textoCifrado4, alfabetoMinPermutado, alfabetoMaxPermutado);
        System.out.println("==== PRUEBA 4 ====");
        System.out.println("Texto original: " + textoOriginal4);
        System.out.println("Texto cifrado: " + textoCifrado4);
        System.out.println("Texto descifrado: " + textoDescifrado4);
        System.out.println();
    
  
    
       
    }
    
}