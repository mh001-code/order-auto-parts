
package model.util;

import model.entidade.ECliente;
import model.persistencia.PCliente;

public class TCliente_inserir {
    
        
    public static void main(String[] args) {
        
        //Objeto de testes, do tipo do Cliente
        ECliente cliente = new ECliente();
        
        //Preenchendo o objeto cliente com valores para testes
        cliente.setNome("Fulano");    
        cliente.setEndereco("Rua 1");    
        cliente.setTelefone(62999999);
        
        //Cria uma instancia da persistencia para usarmos
        PCliente persistencia = new PCliente();
        
        System.out.println("Iniciando a inclusão de um novo cliente...");
        
        persistencia.inserir(cliente);
        
        System.out.println("Cliente incluido com sucesso!");
        
    }
    
}
