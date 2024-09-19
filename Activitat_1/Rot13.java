package Activitat_1;
//programa que encripta y desencripta mensajes con cifrado cesar 13
public class Rot13 {
    public static final char [] diccionarioMin ={
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', 'á', 'é', 'í', 'ó', 'ú', 'ñ', 'ç'};
    public static final char [] diccionarioMax = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', 'Á', 'É', 'Í', 'Ó', 'Ú', 'Ñ', 'Ç'};

    public static void main(String[] args) {
        System.out.println("Texto a cifrar :  Hola Alicia");
        String entrada = "Hola Alicia";
        //cifrar el texto
        String resultado = xifraRot13(entrada);
        System.out.println("El mensaje :" + entrada + "se ha cifrado  a " + resultado);
        //descrifrar el texto 
        String resultadoDescifrado = desxifraRot13(resultado);
        System.out.println("El mensaje cifrado  : "+ resultado+ "se ha descifrado a  : "+ resultadoDescifrado);
    }
    //funcion que cifra con ROT13
    public static  String xifraRot13 (String mensaje){
        String codificado = "";
        for (int i = 0 ; i < mensaje.length() ; i ++){
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)){
                //busca la letra en el diccionario minuscula y ejecuta el desplazamiento
                for(int j = 0 ; j < diccionarioMin.length; j++){
                    if (diccionarioMin[j] == letra){
                        codificado += diccionarioMin[(j+13)% diccionarioMin.length];
                        break;
                    }
                }
            }else if (Character.isUpperCase(letra)){
                //busca en la letra en el diccionario mayuscula y ejecuta el desplazamiento
                for (int l = 0; l < diccionarioMax.length ; l ++){
                    if(diccionarioMax[l] == letra){
                        codificado += diccionarioMax[(l+13)%diccionarioMax.length];
                        break;
                    }
                }
            }
            else{
                codificado += letra ;//espacios y numeros
            }
        }
        return codificado ;
    }
    public static  String desxifraRot13 (String mensaje){
        String descodificado = "";
        for (int i = 0 ; i < mensaje.length() ; i ++){
            char letra = mensaje.charAt(i);
            if (Character.isLowerCase(letra)){
                //busca la letra en el diccionario minuscula y ejecuta el desplazamiento
                for(int j = 0 ; j < diccionarioMin.length; j++){
                    if (diccionarioMin[j] == letra){
                        descodificado += diccionarioMin[(j-13)% diccionarioMin.length];
                        break;
                    }
                }
            }else if (Character.isUpperCase(letra)){
                //busca en la letra en el diccionario mayuscula y ejecuta el desplazamiento
                for (int l = 0; l < diccionarioMax.length ; l ++){
                    if(diccionarioMax[l] == letra){
                        descodificado += diccionarioMax[(l-13)%diccionarioMax.length];
                        break;
                    }
                }
            }
            else{
                descodificado += letra ;//espacios y numeros
            }
        }
        return descodificado ;
        
    }
}