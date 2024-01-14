package src.fundamentos.primeirasAulas;

import java.util.HashMap;
import java.util.Map;

public class Colecoes {
        public static void main(String[] args) {
        Map<String, Integer> notas = new HashMap<>();
        notas.put("Dani", 9);
        notas.put("Vini", 10);
        notas.put("Elias", 10);
        notas.put("Elias", 5);
        
        for(Map.Entry<String, Integer> entry : notas.entrySet()){
            var key = entry.getKey();
            var value = entry.getValue();

            System.out.println("A chave é " + key + "e o valor é " + value);
        }

        
    }
}
