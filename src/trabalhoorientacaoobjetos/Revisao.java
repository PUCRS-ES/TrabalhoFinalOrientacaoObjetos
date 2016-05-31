package trabalhoorientacaoobjetos;

/**
 *
 * @author 15202606
 */
public class Revisao {
    private double pontuacao;
    private long time;
    private String sumario;
    private String texto;
    private String produtoId;
    private String userId;
    //private Utilidade util;
    
    public Revisao(double pontuacao, long time, String sumario, String texto, String produtoId, String userId) {
        this.pontuacao = pontuacao;
        this.time = time;
        this.sumario = sumario;
        this.texto = texto;
        this.produtoId = produtoId;
        this.userId = userId;
    }

    public double getPontuacao() {
        return pontuacao;
    }
    
    public long getTime() {
        return time;
    }
}