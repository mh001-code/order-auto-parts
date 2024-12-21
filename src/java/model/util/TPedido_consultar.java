
package model.util;

import model.entidade.EPedido;
import model.persistencia.PPedido;

public class TPedido_consultar {
    
    public static void main(String[] args) {
        
        //Cria uma instancia da persistencia para usarmos
        PPedido persistencia = new PPedido();
        
        System.out.println("Iniciando a consulta de um pedido...");
        
        EPedido pedido = persistencia.consultar(24);
        
        System.out.println("Codigo.....:" + pedido.getCodigo());
        System.out.println("Data.......:" + pedido.getDatapedido());
        System.out.println("Valor Total:" + pedido.getValortotal());
        System.out.println("Cliente....:" + pedido.getCodigocliente());
        
        System.out.println("Pedido consultado com sucesso!");
    }
    
}
