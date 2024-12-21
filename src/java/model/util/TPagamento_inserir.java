
package model.util;

import model.entidade.EPagamento;
import model.persistencia.PPagamento;

public class TPagamento_inserir {
    
        
    public static void main(String[] args) {
        
        //Objeto de testes, do tipo do Pedido
        EPagamento pagamento = new EPagamento();
        
        //Preenchendo o objeto pedido com valores para testes
        pagamento.setValorTotal(450);
        
        //Cria uma instancia da persistencia para usarmos
        PPagamento persistencia = new PPagamento();
        
        System.out.println("Iniciando a inclusão de um novo pagamento...");
        
        persistencia.inserir(pagamento);
        
        System.out.println("Pagamento incluído com sucesso!");
        
    }
    
}
