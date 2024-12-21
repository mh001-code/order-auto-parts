
package model.util;

import model.entidade.EMetodoPg;
import model.persistencia.PMetodoPg;

public class TMetodoPg_inserir {
    
        
    public static void main(String[] args) {
        
        //Objeto de testes, do tipo do Pedido
        EMetodoPg metodo = new EMetodoPg();
        
        //Preenchendo o objeto pedido com valores para testes
        metodo.setDescricao("Cartão de Crédito");    
        
        //Cria uma instancia da persistencia para usarmos
        PMetodoPg persistencia = new PMetodoPg();
        
        System.out.println("Iniciando a inclusão de um novo metodo de pagamento...");
        
        persistencia.inserir(metodo);
        
        System.out.println("Metodo de pagamento incluído com sucesso!");
        
    }
    
}
