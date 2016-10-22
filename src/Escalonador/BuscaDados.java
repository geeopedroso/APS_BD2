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
public class BuscaDados {

    public BuscaDados() {
    }

    private static MinhaConexao conexao;

    public List<Dados> busca() {
        List<Dados> dados = new ArrayList();
        conexao = new MinhaConexao();
        conexao.getConnection();

        Connection c = conexao.getConnection();

        try {
            String sql = "Select * from schedule";
            PreparedStatement stm = c.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Dados d = new Dados(rs.getInt("idoperacao"),
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
