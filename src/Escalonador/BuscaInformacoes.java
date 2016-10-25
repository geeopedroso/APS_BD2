/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonador;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geovani
 */
public class BuscaInformacoes {

    public BuscaInformacoes() {
    }

    private static MinhaConexao conexao;

    public List<Informacoes> busca(Integer inicio, Integer fim) {
        List<Informacoes> dados = new ArrayList();
        
        conexao = new MinhaConexao();
        conexao.getConnection();
        String sql = "Select * from schedule where idoperacao > ? and idoperacao < ?";
        Connection c = conexao.getConnection();

        try {
            
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, inicio);
            stm.setInt(2, fim);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Informacoes d = new Informacoes(rs.getInt("idoperacao"),
                        rs.getInt("indicetransacao"),
                        rs.getString("operacao"),
                        rs.getString("itemdado"),
                        rs.getString("timestampj"));

                dados.add(d);
            }
        } catch (Exception e) {
            System.out.println("erro busca dados");
        }

        return dados;
    }

    public  Integer pegarPrimeiroIndice(){
        Integer indice = 0;
        conexao = new MinhaConexao();
        conexao.getConnection();
        String sql = "Select min(idoperacao) from schedule";
        Connection c = conexao.getConnection();
        
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            indice = rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Erro na consulta ao primeiro Indice");
            e.printStackTrace();
        }
     return indice;   
    }
    
    public  Integer pegarUltimoIndice(){
        Integer indice = 0;
        conexao = new MinhaConexao();
        conexao.getConnection();
        String sql = "Select max(idoperacao) from schedule";
        Connection c = conexao.getConnection();
        
        try {
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            indice = rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Erro na consulta ao primeiro Indice");
            e.printStackTrace();
        }
     return indice;   
    }
    
}
