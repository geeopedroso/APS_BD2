/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonador;

import Produtor.MinhaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Geovani
 */
public class AgendaDao {

    public AgendaDao() {
    }
    private static MinhaConexao conexao;

    public static void gravarTransacoes(List<Agenda> agenda) {

        conexao = new MinhaConexao();
        conexao.getConnection();
        Connection conn = conexao.getConnection();

        String sql = "INSERT INTO saida(indiceTransacao, operacao, itemDado) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        int i = 0;
        for (i=0; i< agenda.size(); i++) {
            //  Agenda ag = iteratorAgenda.next();

            try {
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, agenda.get(i).getTransacao().getIndice());
                stmt.setString(2, agenda.get(i).getOperacao().getTipo());
                stmt.setString(3, agenda.get(i).getDado().getItemDado());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Erro na insercao da transacao");
                e.printStackTrace();
            }
            
        }
            try {
                conexao.releaseAll(stmt, conn);
            } catch (SQLException e) {
                System.out.println("Erro ao encerrar conexão");
                e.printStackTrace();
            }
        }
}

        /* public static int pegarUltimoIndice() {
        int ultimoIndice = 0;
        minhaConexao = new MinhaConexao();
        Connection conn = minhaConexao.getConnection();
        String sql = "SELECT MAX(indiceTransacao) FROM schedule;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            ultimoIndice = rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Erro na consulta ao ultimo Indice");
            e.printStackTrace();
        }
        return ultimoIndice;
    }
    
         */
    

