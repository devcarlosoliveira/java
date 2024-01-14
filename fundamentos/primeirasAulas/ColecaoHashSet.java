package src.fundamentos.primeirasAulas;

import java.util.HashSet;

public class ColecaoHashSet {
    public static void main(String[] args) {
        HashSet<Integer> numeros = new HashSet<>();
        numeros.add(10);
        numeros.add(11);
        numeros.add(12);
        numeros.add(13);

        numeros.remove(12);

        System.out.println(numeros.contains(12));

        System.out.println("Size: " + numeros.size());

        for(var numero : numeros){
            System.out.println(numero);
        }

        



    }

}
