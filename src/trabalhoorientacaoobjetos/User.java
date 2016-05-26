/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhoorientacaoobjetos;

/**
 *
 * @author 15202606
 */
public class User {
    private String userID;
    private String nomeUsuario;
    //private Revisoes[] revisoes;

    public User(String userID, String nomeUsuario) {
        this.userID = userID;
        this.nomeUsuario = nomeUsuario;
    }

    public String getUserID() {
        return userID;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    
}
