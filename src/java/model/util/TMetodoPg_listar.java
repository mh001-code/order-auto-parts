package model.util;

import java.util.List;
import model.entidade.EMetodoPg;
import model.persistencia.PMetodoPg;

public class TMetodoPg_listar {

    public static void main(String[] args) {

        //Cria uma instancia da persistencia para usarmos
        PMetodoPg persistencia = new PMetodoPg();

        System.out.println("Iniciando a listagem de Metodos de pagamento...");

        List<EMetodoPg> metodos = persistencia.listar();

        for (EMetodoPg metodo : metodos) {
            System.out.println("Codigo.....:" + metodo.getCodigo());
            System.out.println("Descrição.......:" + metodo.getDescricao());
            
            System.out.println("");
        }

        System.out.println("Metodos de pagamento listados com sucesso!");
    }

}
