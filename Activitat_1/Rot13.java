package Activitat_1;

//programa que encripta y desencripta mensajes con cifrado cesar 13
public class Rot13 {
    public static String codificado = "";
    public static final char [] diccionarioMin ={'a','b','c','d'};
    public static final char [] diccionarioMax = {'A','B','C','D'};

    public static void main(String[] args) {
        System.out.println("Texto a cifrar :  Hola Alicia");
        String entrada = "Hola Alicia";
        String resultado = xifraRot13(entrada);
        System.out.println("El mensaje :" + entrada + "se ha cifrado  a " + resultado);
        
        
        
    }

    public static  String xifraRot13 (String mensaje){
        char [] textoChar = new char[mensaje.length()] ;
        for (int i = 0 ; i < mensaje.length() ; i ++){
            textoChar[i] = mensaje.charAt(i);
        }
        //comparar el textoChar con los char de clase de diccionario

        return codificado ;

    }
    public static  String desxifraRot13 (String mensaje){

        return codificado ;

    }

}