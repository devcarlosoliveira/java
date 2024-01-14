package src.fundamentos.pessoa;

public class Professor extends Pessoa {
	private double salario;

	@Override
	public String toString() {

		return "O nome da pessoa é " + nome + " a idade é " + idade + " e o documento é " + cpf + " salário: "
				+ salario;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
}
