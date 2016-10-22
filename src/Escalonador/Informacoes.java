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
public class Informacoes {
    private Integer idOperacao;
    private Integer indiceTransacao;
    private String operacao;
    private String itemDado;
    private String timesTemp;

    public Informacoes(Integer idOperacao, Integer indiceTransacao, String operacao, String itemDado, String timesTemp) {
        this.idOperacao = idOperacao;
        this.indiceTransacao = indiceTransacao;
        this.operacao = operacao;
        this.itemDado = itemDado;
        this.timesTemp = timesTemp;
    }
    
    
    

    public Integer getIdOperacao() {
        return idOperacao;
    }

    public void setIdOperacao(Integer idOperacao) {
        this.idOperacao = idOperacao;
    }

    public Integer getIndiceTransacao() {
        return indiceTransacao;
    }

    public void setIndiceTransacao(Integer indiceTransacao) {
        this.indiceTransacao = indiceTransacao;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getItemDado() {
        return itemDado;
    }

    public void setItemDado(String itemDado) {
        this.itemDado = itemDado;
    }

    public String getTimesTemp() {
        return timesTemp;
    }

    public void setTimesTemp(String timesTemp) {
        this.timesTemp = timesTemp;
    }

    @Override
    public String toString() {
        return "Dados{" + "idOperacao=" + idOperacao + ", indiceTransacao=" + indiceTransacao + ", operacao=" + operacao + ", itemDado=" + itemDado + ", timesTemp=" + timesTemp + '}';
    }
    
    
    
}
