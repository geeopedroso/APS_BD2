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
public class Agenda {
    private Transacao transacao;
    private Dado dado;
    private Operacao operacao;

    public Agenda(Transacao transacao, Dado dado, Operacao operacao) {
        this.transacao = transacao;
        this.dado = dado;
        this.operacao = operacao;
    }

   

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    @Override
    public String toString() {
        return "Agenda{" + "transacao=" + transacao.getIndice() + ", dado=" + dado.getItemDado() + ", operacao=" + operacao.getTipo() + '}' +"\n";
    }
    
    
    
}
