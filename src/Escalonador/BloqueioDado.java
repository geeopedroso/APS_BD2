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
public class BloqueioDado {
    private Transacao trasacao;
    private Dado dado;

    public BloqueioDado(Transacao trasacao, Dado dado) {
        this.trasacao = trasacao;
        this.dado = dado;
    }
    
    public Transacao getTrasacao() {
        return trasacao;
    }

    public void setTrasacao(Transacao trasacao) {
        this.trasacao = trasacao;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    @Override
    public String toString() {
        return "BloqueioDadoooo{" + "trasacao=" + trasacao.getIndice() + ", dado=" + dado.getItemDado() + '}';
    }
    
    
}
