/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonador;

/**
 *
 * @author Geovani
 */
public class Operacao {
    private Transacao transacao;
    private Dado Dado;
    private String tipo;

    public Operacao(Transacao transacao, Dado Dado, String tipo) {
        this.transacao = transacao;
        this.Dado = Dado;
        this.tipo = tipo;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public Dado getDado() {
        return Dado;
    }

    public void setDado(Dado Dado) {
        this.Dado = Dado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

   
    
    
    
    
    
}
