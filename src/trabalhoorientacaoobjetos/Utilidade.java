package trabalhoorientacaoobjetos;

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
