package model.negocio;

import java.sql.Connection;
import java.util.List;
import model.entidade.EItemPedido;
import model.persistencia.PItemPedido;
import model.util.Conexao;

// Classe responsável pela lógica de negócios relacionada aos itens de pedido
public class NItemPedido {

    // Instância da classe de persistência de itens de pedido
    private PItemPedido pItemPedido;

    // Construtor que inicializa a instância de persistência
    public NItemPedido() {
        pItemPedido = new PItemPedido();
    }

    // Método para salvar um item de pedido (inserir ou atualizar)
    public void salvar(EItemPedido itemPedido) {
        Connection cnn = null;
        try {
            cnn = Conexao.getConnection();
            cnn.setAutoCommit(false); // Inicia uma transação

            if (itemPedido.getCodigo() == 0) {
                pItemPedido.inserir(itemPedido); // Insere um novo item de pedido se o código for 0
            } else {
                // Aqui pode ser adicionado o código para atualização de um item de pedido existente
            }

            cnn.commit(); // Confirma a transação
        } catch (Exception e) {
            if (cnn != null) {
                try {
                    cnn.rollback(); // Reverte a transação em caso de erro
                } catch (Exception ex) {
                    System.out.println("Erro ao realizar rollback: " + ex.getMessage());
                }
            }
            System.out.println("Erro ao salvar item do pedido: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close(); // Fecha a conexão
                } catch (Exception ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // Método para excluir itens de pedido com base no código do pedido
    public void excluirPorPedido(int codigoPedido) {
        Connection cnn = null;
        try {
            cnn = Conexao.getConnection();
            cnn.setAutoCommit(false); // Inicia uma transação

            pItemPedido.excluirPorPedido(codigoPedido); // Exclui itens do pedido

            cnn.commit(); // Confirma a transação
        } catch (Exception e) {
            if (cnn != null) {
                try {
                    cnn.rollback(); // Reverte a transação em caso de erro
                } catch (Exception ex) {
                    System.out.println("Erro ao realizar rollback: " + ex.getMessage());
                }
            }
            System.out.println("Erro ao excluir item do pedido: " + e.getMessage());
        } finally {
            if (cnn != null) {
                try {
                    cnn.close(); // Fecha a conexão
                } catch (Exception ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }

    // Método para listar itens de pedido com base no código do pedido
    public List<EItemPedido> listarPorPedido(int codigoPedido) {
        Connection cnn = null;
        try {
            cnn = Conexao.getConnection();
            return pItemPedido.listarPorPedido(codigoPedido); // Retorna a lista de itens do pedido
        } catch (Exception e) {
            System.out.println("Erro ao listar itens do pedido: " + e.getMessage());
            return null;
        } finally {
            if (cnn != null) {
                try {
                    cnn.close(); // Fecha a conexão
                } catch (Exception ex) {
                    System.out.println("Erro ao fechar conexão: " + ex.getMessage());
                }
            }
        }
    }
}
