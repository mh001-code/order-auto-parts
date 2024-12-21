package model.negocio;

import java.util.List;
import model.entidade.EPagamento;
import model.persistencia.PPagamento;

// Classe responsável pela lógica de negócios relacionada aos pagamentos
public class NPagamento {

    // Instância da classe de persistência de pagamentos
    private PPagamento persistencia;

    // Construtor que inicializa a instância de persistência
    public NPagamento() {
        persistencia = new PPagamento();
    }

    // Método para salvar um pagamento (inserir ou alterar)
    public void salvar(EPagamento pagamento) throws Exception {
        if (pagamento.getCodigo() == 0) {
            persistencia.inserir(pagamento); // Insere um novo pagamento se o código for 0
        } else {
            persistencia.alterar(pagamento); // Altera um pagamento existente se o código for diferente de 0
        }
    }

    // Método para excluir um pagamento pelo código
    public void excluir(int codigo) {
        persistencia.excluir(codigo);
    }

    // Método para consultar um pagamento pelo código
    public EPagamento consultar(int codigo) {
        return persistencia.consultar(codigo);
    }

    // Método para listar todos os pagamentos
    public List<EPagamento> listar() {
        return persistencia.listar();
    }
}
