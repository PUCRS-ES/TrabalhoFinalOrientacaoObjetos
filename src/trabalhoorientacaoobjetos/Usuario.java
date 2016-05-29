package trabalhoorientacaoobjetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 15202606
 */
public class Usuario {
    private String userId;
    private String nomeUsuario;
    private List<Revisao> revisoes;

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
}
