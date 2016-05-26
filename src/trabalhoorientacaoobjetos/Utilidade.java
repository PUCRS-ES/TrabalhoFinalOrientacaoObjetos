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
public class Utilidade {
    private int positivas;
    private int total;

    public Utilidade(int total) {
        this.total = total;
    }

    public int getPositivas() {
        return positivas;
    }

    public int getTotal() {
        return total;
    }

    public void setPositivas(int positivas) {
        this.positivas = positivas;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    public int Utilidade(){
        return positivas/total;
    }
    
}
