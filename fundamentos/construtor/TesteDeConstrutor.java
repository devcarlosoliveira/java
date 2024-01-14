package src.fundamentos.construtor;

/**
 * TesteDeConstrutor
 */
public class TesteDeConstrutor {

	public static void main(String[] args) {
		Construtor construtor = new Construtor(1);

		construtor.setNumero(2);

		System.out.println(construtor.getNumero());
	}

}
