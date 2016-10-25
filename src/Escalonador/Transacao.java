/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonador;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geovani
 */
public class Transacao {
    
    private Integer indice;
    private List<Operacao> operacoes;

    public Transacao(int indice) {
        this.indice = indice;
        operacoes = new ArrayList<>();
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    

    public List<Operacao> getOperacoes() {
        return operacoes;
    }

    public void setOperacoes(List<Operacao> operacoes) {
        this.operacoes = operacoes;
    }

    
    
}
