package trabalhoorientacaoobjetos;

import static java.nio.file.Files.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;


/**
 *
 * @author 15202606
 */
public class Produto {
    public static final double PRECO_NAO_IDENTIFICADO = -1;
    public static final double PRECO_INDEFINIDO = -2;
    private String produtoID;
    private String titulo;
    private double preco;
    private List<Revisao> revisoes;

    public Produto(String produtoID, String titulo, double preco) {
        this.revisoes = new ArrayList<>();
        this.produtoID = produtoID;
        this.titulo = titulo;
        this.preco = preco;
    }

    Produto(Produto consultaProdutosPorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getPrecoNaoIndentificado() {
        return PRECO_NAO_IDENTIFICADO;
    }

    public double getPrecoIndefinido() {
        return PRECO_INDEFINIDO;
    }

    public String getProdutoID() {
        return produtoID;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getPreco() {
        return preco;
    }

    public void setProdutoID(String produtoID) {
        this.produtoID = produtoID;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
    public void addRevisao(Revisao review) {
        this.revisoes.add(review);
    }
    
    public List<Revisao> getRevisoes(){
        return revisoes;
    }
  
}
