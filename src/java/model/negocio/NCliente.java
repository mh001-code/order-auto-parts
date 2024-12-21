package model.negocio;

import java.util.List;
import model.entidade.ECliente;
import model.persistencia.PCliente;

// Classe responsável pela lógica de negócios relacionada aos clientes
public class NCliente {
    
    // Instância da classe de persistência de clientes
    PCliente pcl;

    // Construtor que inicializa a instância de persistência
    public NCliente() {
        pcl = new PCliente();
    }

    // Método para salvar um cliente (inserir ou alterar)
    public void salvar(ECliente cliente) {
        if (cliente.getCodigo() == 0) {
            pcl.inserir(cliente); // Insere um novo cliente se o código for 0
        } else {
            pcl.alterar(cliente); // Altera um cliente existente se o código for diferente de 0
        }
    }

    // Método para excluir um cliente pelo código
    public void excluir(int codigo) {
        pcl.excluir(codigo);
    }

    // Método para consultar um cliente pelo código
    public ECliente consultar(int codigo) {
        return pcl.consultar(codigo);
    }

    // Método para listar todos os clientes
    public List<ECliente> listar() {
        return pcl.listar();
    }
}
