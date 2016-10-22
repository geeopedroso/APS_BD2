/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Escalonador;

import Produtor.Transacao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Geovani
 */
public class AgrupaTransacao {

    BuscaInformacoes b = new BuscaInformacoes();
    List<Informacoes> dados = b.busca();

    
    
    public List<Transacao> Agrupa() {
        List<Transacao> t = new ArrayList<>();
        int i = 0;
        while(!dados.isEmpty()){
            for(i = 0; i < dados.size(); i++){
                
                
            }
            
            
        }

        return t;

    }

}
