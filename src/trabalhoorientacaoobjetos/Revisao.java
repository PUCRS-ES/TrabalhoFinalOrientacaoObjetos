package trabalhoorientacaoobjetos;

public class Revisao {
    private double pontuacao;
    private long time;
    private String sumario;
    private String texto;
    private String produtoId;
    private String userId;
    private int utilPositivo;
    private int utilTotal;
    
    public Revisao(double pontuacao, long time, String sumario, String texto, String produtoId, String userId, int positivo, int total) {
        this.pontuacao = pontuacao;
        this.time = time;
        this.sumario = sumario;
        this.texto = texto;
        this.produtoId = produtoId;
        this.userId = userId;
        this.utilPositivo = positivo;
        this.utilTotal = total;
    }

    public double getPontuacao() {
        return pontuacao;
    }
    
    public long getTime() {
        return time;
    }
    
    public int getUtilPositivo() {
        return utilPositivo;
    }
    
    public int getUtilTotal() {
        return utilTotal;
    }
    
    public String getTexto(){
        return texto;
    }

    public String getSumario() {
        return sumario;
    }

    public String getProdutoId() {
        return produtoId;
    }

    public String getUserId() {
        return userId;
    }
    
    
}