package trabalhoorientacaoobjetos;

/**
 *
 * @author 15202606
 */
public class Usuario {
    private String userId;
    private String nomeUsuario;
    //private Revisoes[] revisoes;

    public Usuario(String userId, String nomeUsuario) {
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
    
}
