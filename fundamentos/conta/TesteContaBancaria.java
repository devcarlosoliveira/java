package src.fundamentos.conta;

/**
 * TesteContaBancaria
 */
public class TesteContaBancaria {

	public static void main(String[] args) {
		var contaBancaria = new ContaBancaria();

		contaBancaria.setNumero("1234");
		contaBancaria.setTitular("Jose");

		contaBancaria.depositar(150);
		contaBancaria.sacar(50);

	}
}