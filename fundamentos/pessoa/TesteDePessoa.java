package src.fundamentos.pessoa;

/**
 * TesteDePessoa
 */
public class TesteDePessoa {

	public static void main(String[] args) {

		var pessoa = new Pessoa();
		pessoa.setCpf("123456789");
		pessoa.setNome("Daniele");
		pessoa.setIdade(33);

		System.out.println(pessoa);

		var professor = new Professor();
		professor.setCpf("123456789");
		professor.setNome("Daniele");
		professor.setIdade(33);
		professor.setSalario(33);

		System.out.println(professor);

		var aluno = new Aluno();
		aluno.setCpf("123456789");
		aluno.setNome("Daniele");
		aluno.setIdade(33);
		aluno.setMatricula("123456");

		System.out.println(aluno);

	}
}