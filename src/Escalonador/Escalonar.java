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

    public boolean donoDoBloqueio(List<BloqueioDado> bloqueados, Dado d, Transacao t) {

        for (BloqueioDado b : bloqueados) {
            if (b.getDado().getItemDado().equals(d.getItemDado())) {
                if (t.getIndice().equals(b.getTrasacao().getIndice())) {
                    return true;
                }
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
        System.out.println("aaaaaa");
        for(Agenda e: terminado){
            System.out.println("aaa"+e);
        }
        
        while (!agenda.isEmpty()) {
            Transacao t = agenda.get(0).getTransacao();
                    Dado d = agenda.get(0).getDado();
                    Operacao op = agenda.get(0).getOperacao();
                    
            switch (agenda.get(0).getOperacao().getTipo()) {
                case "S":
                    agenda.remove(0);
                    break;
                case "R":
                    if (esc.estaBloqueado(bloqueioExclusivo, d) &&
                            !esc.donoDoBloqueio(bloqueioExclusivo, d, t)) {
                        System.out.println("\n dado bloqueado exclusivo : " + d);

                        emEspera.add(agenda.get(0));

                    } else {
                        if (esc.estaBloqueado(bloqueioCompartilhado, d)) {
                            System.out.println(" ja ta bloqueado compartilhado");

                        } else {
                            bloqueioCompartilhado.add(new BloqueioDado(t, d));
                        }
                        System.out.println("\n transacao : " + t.getIndice()
                                + "read " + d.getItemDado());
                        terminado.add(new Agenda(t, d, op));
                    }
                    agenda.remove(0);
                    break;

                case "W":
                    
                    if (esc.estaBloqueado(bloqueioExclusivo, d)
                            && !esc.donoDoBloqueio(bloqueioExclusivo, d, t)
                            || esc.estaBloqueado(bloqueioCompartilhado, d)
                            && !esc.donoDoBloqueio(bloqueioCompartilhado, d, t)) {

                        System.out.println("dado bloqueado : " + d.getItemDado());

                        emEspera.add(agenda.get(0));

                    } else {
                        if (esc.estaBloqueado(bloqueioExclusivo, d) && esc.donoDoBloqueio(bloqueioExclusivo, d, t)) {
                            System.out.println("\n ja sou o dono");
                        } else {
                            bloqueioExclusivo.add(new BloqueioDado(agenda.get(0).getTransacao(), agenda.get(0).getDado()));

                        }
                        System.out.println("\n transacao : " + agenda.get(0).getTransacao().getIndice()
                                + "write" + agenda.get(0).getDado().getItemDado());
                        terminado.add(new Agenda(t, d, op));
                    }
                    agenda.remove(0);
                    break;

                case "E":

                    System.out.println(" end transacao: " + agenda.get(0).getTransacao().getIndice());

                    List<Dado> dadosdesbloqueados = esc.desbloqueia(bloqueioExclusivo, agenda.get(0).getTransacao().getIndice());
                    dadosdesbloqueados.addAll(dadosdesbloqueados.size(),
                            esc.desbloqueia(bloqueioCompartilhado, agenda.get(0).getTransacao().getIndice()));

                    terminado.add(new Agenda(t, d, op));
                    agenda.remove(0);

                    esc.acorda(emEspera, dadosdesbloqueados, agenda);

                    break;

            }
            
        }
        
        //AgendaDao.gravarTransacoes(terminado);
        for(Agenda e: terminado){
            System.out.println(e);
        }

    }
}
