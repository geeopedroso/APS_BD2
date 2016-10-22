/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produtor;

/**
 *
 * @author Geovani
 */

    public enum Acesso {
    READ("R"),
    WRITE("W"),
    START("S"),
    END("E"),
    COMMIT("C"),
    ABORT("A");
    String texto;

    private Acesso(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return texto;
    }
}

    

