package src.fundamentos.pessoa;

/**
 * Aluno
 */
public class Aluno extends Pessoa {
    private String matricula;

    @Override
    public String toString() {
        
        return "O nome da pessoa é " + nome + " a idade é " + idade + " e o documento é " + cpf + " matricula: " + matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
}
