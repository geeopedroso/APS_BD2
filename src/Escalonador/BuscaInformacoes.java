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

    public List<Informacoes> busca() {
        List<Informacoes> dados = new ArrayList();
        
        conexao = new MinhaConexao();
        conexao.getConnection();

        Connection c = conexao.getConnection();

        try {
            String sql = "Select * from schedule";
            PreparedStatement stm = c.prepareStatement(sql);

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

}
