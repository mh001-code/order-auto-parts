
package model.util;

import java.time.Instant;
import java.util.Date;
import model.entidade.EPedido;
import model.persistencia.PPedido;

public class TPedido_alterar {
    
    public static void main(String[] args) {
        
        //Objeto de testes, do tipo do Pedido
        EPedido pedido = new EPedido();
        
        //Preenchendo o objeto pedido com valores para testes
        pedido.setDatapedido(Date.from(Instant.EPOCH));
        pedido.setValortotal(124);
        pedido.setIdPedido(24);
        
        
        //Cria uma instancia da persistencia para usarmos
        PPedido persistencia = new PPedido();
        
        System.out.println("Iniciando a ALTERAÇÃO do pedido...");
        
        //persistencia.alterar(pedido);
        
        System.out.println("Pedido alterado com sucesso!");
    }
    
}
