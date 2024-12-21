package model.negocio;

import java.util.List;
import model.entidade.EMetodoPg;
import model.persistencia.PMetodoPg;

// Classe responsável pela lógica de negócios relacionada aos métodos de pagamento
public class NMetodoPg {
    
    // Instância da classe de persistência de métodos de pagamento
    private final PMetodoPg pcl;

    // Construtor que inicializa a instância de persistência
    public NMetodoPg() {
        pcl = new PMetodoPg();
    }
    
    // Método para salvar um método de pagamento (inserir ou alterar)
    public void salvar(EMetodoPg metodo) {
        if (metodo.getCodigo() == 0) {
            pcl.inserir(metodo); // Insere um novo método de pagamento se o código for 0
        } else {
            pcl.alterar(metodo); // Altera um método de pagamento existente se o código for diferente de 0
        }
    }
    
    // Método para excluir um método de pagamento pelo código
    public void excluir(int codigo) {
        pcl.excluir(codigo);
    }
    
    // Método para consultar um método de pagamento pelo código
    public EMetodoPg consultar(int codigo) {
        return pcl.consultar(codigo);
    }
    
    // Método para listar todos os métodos de pagamento
    public List<EMetodoPg> listar() {
        return pcl.listar();
    }
}
