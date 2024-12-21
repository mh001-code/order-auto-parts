package model.negocio;

import java.util.List;
import model.entidade.EProduto;
import model.persistencia.PProduto;

// Classe responsável pela lógica de negócios relacionada aos produtos
public class NProduto {
    
    // Instância da classe de persistência de produtos
    PProduto pcl;

    // Construtor que inicializa a instância de persistência
    public NProduto() {
        pcl = new PProduto();
    }

    // Método para salvar um produto (inserir ou alterar)
    public void salvar(EProduto produto) {
        if (produto.getCodigo() == 0) {
            pcl.inserir(produto); // Insere um novo produto se o código for 0
        } else {
            pcl.alterar(produto); // Altera um produto existente se o código for diferente de 0
        }
    }

    // Método para excluir um produto pelo código
    public void excluir(int codigo) {
        pcl.excluir(codigo);
    }

    // Método para consultar um produto pelo código
    public EProduto consultar(int codigo) {
        return pcl.consultar(codigo);
    }

    // Método para listar todos os produtos
    public List<EProduto> listar() {
        return pcl.listar();
    }
}
