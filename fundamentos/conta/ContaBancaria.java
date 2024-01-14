package src.fundamentos.conta;

/**
 * ContaBancaria
 */
public class ContaBancaria {

	private String numero;
	private String titular;
	private double saldo;

	public ContaBancaria() {
		this.saldo = 0.0;
	}

	public ContaBancaria(String numero, String titular, double saldo) {
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
	}

	void depositar(double valor) {

		if (valor < 0) {
			System.out.println("O valor de depósito é inválido");
		}

		if (valor > 0) {

			saldo += valor;
			System.out.println("Depósito de R$ " + valor + ". Saldo atual R$ " + saldo);

		}
	}

	void sacar(double valor) {

		if (valor < 0 || valor > saldo) {
			System.out.println("O valor do saque é inválido");
		}

		if (valor > 0) {

			saldo -= valor;
			System.out.println("Saque de R$ " + valor + ". Saldo atual R$ " + saldo);

		}
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

}