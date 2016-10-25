/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Geovani
 */
public class Escalonar {

    /*
    Função responsável por preencher a lista de transações a ser serializadas.
     */
    List<Agenda> preencherAgenda(Integer inicio, Integer fim) {
        BuscaInformacoes b = new BuscaInformacoes();
        List<Informacoes> info = b.busca(inicio-1,fim+1);

        List<Agenda> emExecucao = new ArrayList<>();

        for (int i = 0; i < info.size(); i++) {
            Transacao t = new Transacao(info.get(i).getIndiceTransacao());
            Dado d = new Dado(info.get(i).getItemDado());

            Agenda ag = new Agenda(t, d, new Operacao(t, d, info.get(i).getOperacao()));
            emExecucao.add(ag);

        }

        return emExecucao;
    }

    /*
    Função que verifica se o dado fornecido está bloqueado.
     */
    public boolean estaBloqueado(List<BloqueioDado> bloqueados, Dado d) {

        for (BloqueioDado b : bloqueados) {
            if (b.getDado().getItemDado().equals(d.getItemDado())) {
                return true;
            }
        }
        return false;
    }

    /*
    Função que verifica se a transação informada é dono do bloqueio do dado também informado.
     */
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

    /*
    Função que retira da lista de bloqueados, os dados que foram bloqueados por uma transação.
     */
    public List<Dado> desbloqueia(List<BloqueioDado> bloqueados, Integer indiceTransacao) {
        List<Dado> dados = new ArrayList<>();
        Iterator<BloqueioDado> bloqueioIterator = bloqueados.iterator();
        while (bloqueioIterator.hasNext()) {
            BloqueioDado b = bloqueioIterator.next();
            if (b.getTrasacao().getIndice().equals(indiceTransacao)) {
                Dado d = new Dado(b.getDado().getItemDado());
                dados.add(d);
                bloqueioIterator.remove();

            }
        }
        return dados;
    }

    /*
    Função que devolve as transações que estava em espera para a lista de executando, a partir do dado desbloqueado
    recentemente.
     */
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

    public boolean isTransacaoEstaCompleta(List<Agenda> agenda, Integer idtransacao) {

        for (Agenda ag : agenda) {
            if (ag.getTransacao().getIndice().equals(idtransacao)) {
                return true;
            }
        }

        return false;
    }

    private static Scanner scanner;

    public static void main(String[] args) throws InterruptedException {
        scanner = new Scanner(System.in);
        Escalonar esc = new Escalonar();
        BuscaInformacoes b = new BuscaInformacoes();
        Integer primeiroIndice = b.pegarPrimeiroIndice();
        Integer ultimo = 0; //flag pra saber aonde parou o select do banco
        Integer tamanhoDoSelect = 30 ;
        Integer fim = b.pegarUltimoIndice();
       //enquanto nao chegar ao fim do banco de dados, o programa vai ficar rodando
        while (ultimo < fim) {
            ultimo = (primeiroIndice + tamanhoDoSelect);
            
            List<Agenda> agenda = esc.preencherAgenda(primeiroIndice,ultimo);//Lista de operações a serem executadas.

            List<Agenda> emEspera = new ArrayList<>();//Lista de transações a espera de um dado bloqueado.
            List<BloqueioDado> bloqueioCompartilhado = new ArrayList<>();
            List<BloqueioDado> bloqueioExclusivo = new ArrayList<>();
            List<Agenda> terminado = new ArrayList<>();//Lista de operações já serializadas.
           
            
            
            /*
            Enquanto houver registos à serem serializados 
            */            
            while (!agenda.isEmpty()) {
                Transacao t = agenda.get(0).getTransacao();
                Dado d = agenda.get(0).getDado();
                Operacao op = agenda.get(0).getOperacao();

                switch (agenda.get(0).getOperacao().getTipo()) {
                    case "S":
                        
                        agenda.remove(0);
                        break;
                    case "R":
                        if (esc.estaBloqueado(bloqueioExclusivo, d)
                                && !esc.donoDoBloqueio(bloqueioExclusivo, d, t)) {

                            emEspera.add(agenda.get(0));

                        } else {
                            if (esc.estaBloqueado(bloqueioCompartilhado, d)) {

                            } else {
                                bloqueioCompartilhado.add(new BloqueioDado(t, d));
                            }
                            terminado.add(new Agenda(agenda.get(0).getTransacao(), agenda.get(0).getDado(), agenda.get(0).getOperacao()));
                        }
                        agenda.remove(0);
                        break;

                    case "W":

                        if (esc.estaBloqueado(bloqueioExclusivo, d)
                                && !esc.donoDoBloqueio(bloqueioExclusivo, d, t)
                                || esc.estaBloqueado(bloqueioCompartilhado, d)
                                && !esc.donoDoBloqueio(bloqueioCompartilhado, d, t)) {

                            emEspera.add(agenda.get(0));

                        } else {
                            if (esc.estaBloqueado(bloqueioExclusivo, d) && esc.donoDoBloqueio(bloqueioExclusivo, d, t)) {
                            } else {
                                bloqueioExclusivo.add(new BloqueioDado(t, d));

                            }
                            terminado.add(new Agenda(t, d, op));
                        }
                        agenda.remove(0);
                        break;

                    case "E":

                        List<Dado> dadosdesbloqueados = esc.desbloqueia(bloqueioExclusivo, agenda.get(0).getTransacao().getIndice());
                        dadosdesbloqueados.addAll(dadosdesbloqueados.size(),
                                esc.desbloqueia(bloqueioCompartilhado, agenda.get(0).getTransacao().getIndice()));
                        op.setTipo("C");
                        terminado.add(new Agenda(t, d, op));
                        agenda.remove(0);

                        esc.acorda(emEspera, dadosdesbloqueados, agenda);

                        break;

                }

            }

            AgendaDao.gravarTransacoes(terminado);
            primeiroIndice = ultimo+1;
            fim = b.pegarUltimoIndice();
           
            System.out.println("coletou");
            System.out.println(" presione ctrl + c para interromper");
            Thread.sleep(8000);
            
        }
    }

}
