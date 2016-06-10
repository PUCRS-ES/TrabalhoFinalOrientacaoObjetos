package trabalhoorientacaoobjetos;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String userId;
    private String nomeUsuario;
    private List<Revisao> revisoes;
    private Double mediaDasAvaliacoes;

    public Usuario(String userId, String nomeUsuario) {
        this.revisoes = new ArrayList<>();
        this.userId = userId;
        this.nomeUsuario = nomeUsuario;
    }

    public String getUserId() {
        return userId;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setUserID(String userId) {
        this.userId = userId;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
    public void addRevisao(Revisao review) {
        this.revisoes.add(review);
    }
    
    public int getQuantidadeRevisoes() {
        return this.revisoes.size();
    }
    
    public void calculaMediaDasAvaliacoesDoUsuario() {
        double avaliacoesPositivas = 0;
        double avaliacoesTotal = 0;
        for (Revisao revisao : revisoes) {
            avaliacoesPositivas += revisao.getUtilPositivo();
            avaliacoesTotal += revisao.getUtilTotal();
        }
        if (avaliacoesTotal == 0)
            mediaDasAvaliacoes = 0.0;
        else
            mediaDasAvaliacoes = avaliacoesPositivas / avaliacoesTotal;
    }
    
    public Double getMediaDasAvaliacoesDoUsuario() {
        return mediaDasAvaliacoes;
    }
    
    public List<Revisao> getRevisoes() {
        return revisoes;
    }
    
}
