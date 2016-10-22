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
public class Escalonar {

    List<Agenda> preencheEmExecucao() {
        BuscaInformacoes b = new BuscaInformacoes();
        List<Informacoes> info = b.busca();

        List<Agenda> emExecucao = new ArrayList<>();

        for (int i = 0; i < info.size(); i++) {
            Transacao t = new Transacao(info.get(i).getIndiceTransacao());
            Dado d = new Dado(info.get(i).getItemDado());

            Agenda ag = new Agenda(t, d, new Operacao(t, d, info.get(i).getOperacao()));
            emExecucao.add(ag);

        }

        return emExecucao;
    }

    public static void main(String[] args) {
        Escalonar esc = new Escalonar();
        List<Agenda> agenda = esc.preencheEmExecucao();
        List<BloqueioDado> bloqueioCompartilhado;
        List<BloqueioDado> bloqueioExclusivo;

        
        
        
        
    }
}
