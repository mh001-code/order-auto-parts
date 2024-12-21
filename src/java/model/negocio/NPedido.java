package model.negocio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entidade.EPedido;
import model.persistencia.PPedido;

// Classe responsável pela lógica de negócios relacionada aos pedidos
public class NPedido {

    // Instância da classe de persistência de pedidos
    private PPedido persistencia;

    // Construtor que inicializa a instância de persistência
    public NPedido() {
        persistencia = new PPedido();
    }

    // Método para salvar um pedido (inserir ou alterar)
    public void salvar(EPedido pedido) throws Exception {
        Logger.getLogger(NPedido.class.getName()).log(Level.INFO, "Salvando pedido: {0}", pedido);
        if (pedido.getCodigo() == 0) {
            persistencia.inserir(pedido); // Insere um novo pedido se o código for 0
        } else {
            persistencia.alterar(pedido); // Altera um pedido existente se o código for diferente de 0
        }
    }

    // Método para excluir um pedido pelo código
    public void excluir(int codigo) {
        Logger.getLogger(NPedido.class.getName()).log(Level.INFO, "Excluindo pedido com código: {0}", codigo);
        persistencia.excluir(codigo);
    }

    // Método para consultar um pedido pelo código
    public EPedido consultar(int codigo) {
        Logger.getLogger(NPedido.class.getName()).log(Level.INFO, "Consultando pedido com código: {0}", codigo);
        return persistencia.consultar(codigo);
    }

    // Método para listar todos os pedidos
    public List<EPedido> listar() {
        Logger.getLogger(NPedido.class.getName()).log(Level.INFO, "Listando pedidos");
        return persistencia.listar();
    }
}
