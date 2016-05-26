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
public class Produto {
    public final double precoNaoIndentificado = -1;
    public final double precoIndefinido = -2;
    private String produtoID;
    private String titulo;
    private double preco;
    //private Revisoes[] revisoes;

    public Produto(String produtoID, String titulo, double preco) {
        this.produtoID = produtoID;
        this.titulo = titulo;
        this.preco = preco;
    }

    public double getPrecoNaoIndentificado() {
        return precoNaoIndentificado;
    }

    public double getPrecoIndefinido() {
        return precoIndefinido;
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
    
    
}
