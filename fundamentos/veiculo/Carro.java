package src.fundamentos.veiculo;

/**
 * Carro
 */
public class Carro implements Veiculo {

	@Override
	public void acelerar() {
		System.out.println("Aceleranto o carro");
	}

	@Override
	public void frear() {
		System.out.println("Freando o carro");
	}

}