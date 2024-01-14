package src.fundamentos.primeirasAulas;

import java.util.ArrayList;
import java.util.List;

/**
 * EstruturaDeDados
 */
public class EstruturaDeDados {

    public static void main(String[] args) {
        List<String> nomes = new ArrayList<>();
        nomes.add("Dani");
        nomes.add("Vini");
        nomes.add("Elias");

        System.out.println(nomes.get(2));

        for (var nome : nomes){
            System.out.println(nome);
        }

        nomes.forEach(nome -> System.out.println(nome));

        nomes.forEach(System.out::println);

    }
}
