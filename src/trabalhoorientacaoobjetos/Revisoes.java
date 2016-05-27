package trabalhoorientacaoobjetos;

/**
 *
 * @author 15202606
 */
public class Revisoes {
    private double pontuacao;
    private long time;
    private String sumario;
    private String texto;
    //private Produto produtos;
    //private User usuario;
    //private Utilidade util;
    
    public Revisoes(double pontuacao, long time, String sumario, String texto) {
        this.pontuacao = pontuacao;
        this.time = time;
        this.sumario = sumario;
        this.texto = texto;
    }
}
