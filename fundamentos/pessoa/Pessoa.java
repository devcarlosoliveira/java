package src.fundamentos.pessoa;

/**
 * Pessoa
 */
public class Pessoa {

    protected String nome;
    protected int idade;
    protected String cpf;

    String getDados() {
        return "O nome da pessoa é " + nome + " a idade é " + idade + " e o documento é " + cpf;
    }

    @Override
    public String toString() {
        return "O nome da pessoa é " + nome + " a idade é " + idade + " e o documento é " + cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}