/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Geovani
 */
public class Escalonar {

    List<Agenda> preencherAgenda() {
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

    public boolean estaBloqueado(List<BloqueioDado> bloqueados, Dado d) {

        for (BloqueioDado b : bloqueados) {
            if (b.getDado().getItemDado().equals(d.getItemDado())) {
                return true;
            }
        }
        return false;
    }

    public List<Dado> desbloqueia(List<BloqueioDado> bloqueados, Integer indice) {
        List<Dado> dados = new ArrayList<>();
        Iterator<BloqueioDado> bloqueioIterator = bloqueados.iterator();
        while (bloqueioIterator.hasNext()) {
            BloqueioDado b = bloqueioIterator.next();
            if (b.getTrasacao().getIndice().equals(indice)) {
                Dado d = new Dado(b.getDado().getItemDado());
                dados.add(d);
                bloqueioIterator.remove();

            }
        }
        return dados;
    }

    public void acorda(List<Agenda> emEspera, List<Dado> dados, List<Agenda> executando) {

        Iterator<Dado> iteratordado = dados.iterator();
        while (iteratordado.hasNext()) {
            Dado d = iteratordado.next();

            for (int i = 0; i < emEspera.size(); i++) {
                if (emEspera.get(i).getDado().getItemDado().equals(d.getItemDado())) {
                    executando.add(0, new Agenda(emEspera.get(i).getTransacao(), emEspera.get(i).getDado(),
                            emEspera.get(i).getOperacao()));
                    emEspera.remove(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        Escalonar esc = new Escalonar();
        List<Agenda> agenda = esc.preencherAgenda();

        List<Agenda> executando = new ArrayList<>();
        List<Agenda> emEspera = new ArrayList<>();
        List<BloqueioDado> bloqueioCompartilhado = new ArrayList<>();
        List<BloqueioDado> bloqueioExclusivo = new ArrayList<>();
        List<Agenda> terminado = new ArrayList<>();
        int x = 0;
        while (x < 20) {
            switch (agenda.get(0).getOperacao().getTipo()) {
                case "S":
                    agenda.remove(0);
                    break;
                case "R":
                    if (esc.estaBloqueado(bloqueioExclusivo, agenda.get(0).getDado())) {
                        System.out.println("\n dado bloqueado exclusivo : " + agenda.get(0).getDado().getItemDado());

                        emEspera.add(agenda.get(0));

                    } else {
                        if (esc.estaBloqueado(bloqueioCompartilhado, agenda.get(0).getDado())) {
                            System.out.println(" ja ta bloqueado compartilhado");

                        } else {
                            bloqueioCompartilhado.add(new BloqueioDado(agenda.get(0).getTransacao(), agenda.get(0).getDado()));
                        }
                        System.out.println("\n transacao : " +agenda.get(0).getTransacao().getIndice()+
                                  "read " + agenda.get(0).getDado().getItemDado());
                    }
                    agenda.remove(0);
                    break;

                case "W":
                    if (esc.estaBloqueado(bloqueioCompartilhado, agenda.get(0).getDado())
                            || esc.estaBloqueado(bloqueioExclusivo, agenda.get(0).getDado())) {

                        System.out.println("dado bloqueado : " + agenda.get(0).getDado().getItemDado());

                        emEspera.add(agenda.get(0));

                    } else {

                        bloqueioExclusivo.add(new BloqueioDado(agenda.get(0).getTransacao(), agenda.get(0).getDado()));
                        System.out.println("\n transacao : " +agenda.get(0).getTransacao().getIndice()+
                                "write" + agenda.get(0).getDado().getItemDado());
                    }
                    agenda.remove(0);
                    break;

                case "E":

                    System.out.println("\n aaannntessss");
                    System.out.println("\n   bloqueados exclusivo");
                    for (BloqueioDado b : bloqueioExclusivo) {
                        System.out.println(b);
                    }

                    System.out.println("\n    bloqueados compartilhado");
                    for (BloqueioDado b : bloqueioCompartilhado) {
                        System.out.println(b);
                    }

                    System.out.println(" end transacao: " + agenda.get(0).getTransacao().getIndice());
                    List<Dado> dadosdesbloqueados = esc.desbloqueia(bloqueioExclusivo, agenda.get(0).getTransacao().getIndice());
                    dadosdesbloqueados.addAll(dadosdesbloqueados.size(),
                            esc.desbloqueia(bloqueioCompartilhado, agenda.get(0).getTransacao().getIndice()));

                    System.out.println(" depoiiisssss");
                    System.out.println("\n   bloqueados exclusivo");
                    for (BloqueioDado b : bloqueioExclusivo) {
                        System.out.println(b);
                    }

                    System.out.println("\n    bloqueados compartilhado");
                    for (BloqueioDado b : bloqueioCompartilhado) {
                        System.out.println(b);
                    }

                    agenda.remove(0);

                    esc.acorda(emEspera, dadosdesbloqueados, agenda);

                    break;

            }
            x++;
        }

    }
}
