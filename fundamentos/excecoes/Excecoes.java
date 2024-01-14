package src.fundamentos.excecoes;

/**
 * Excecoes
 */
public class Excecoes {

	public static void main(String[] args) {
		try {
			validarNumero();
		} catch (Exception e) {
			System.out.println("Deu ruim");
			e.printStackTrace();
		}
	}

	private static void validarNumero() throws Exception {
		var numero = 10;
		if (numero < 100) {
			throw new Exception("O número é menor que 100");
		}
	}
}
