package Produtor;


import Escalonador.BuscaDados;
import Escalonador.Dados;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int numeroItens = 2;
    private static int numeroTransacoes = 2;
    private static int numeroAcessos = 2;
    private static Scanner scanner;

    public static void main(String[] args) {
        BuscaDados b = new BuscaDados();
        List<Dados> d = b.busca();
        for(int i = 0; i<d.size(); i++){
            System.out.println(d.get(i));
        }
       /* scanner = new Scanner(System.in);
        System.out.println("Criando transacoes e gravando no banco...");
        Produtor produtor = new Produtor(numeroItens, numeroTransacoes, numeroAcessos);
        produtor.start();
        System.out.println("Pressione Enter para encerrar a producao!");

        
        if (scanner.hasNextLine()) {
            System.out.println("Producao encerrada");
            produtor.setFlag(false);

        }
    */
    }
}
