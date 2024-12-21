
package model.util;

import model.persistencia.PPedido;

public class TPedido_excluir {
    
    public static void main(String[] args) {
        
        //Cria uma instancia da persistencia para usarmos
        PPedido persistencia = new PPedido();
        
        System.out.println("Iniciando a exclusão de um cliente...");
        
        persistencia.excluir(4);
        
        System.out.println("Cliente excluído com sucesso!");
    }
    
}
