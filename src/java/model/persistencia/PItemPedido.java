package model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.entidade.EItemPedido;
import model.util.Conexao;

// Classe responsável pela persistência de dados da entidade ItemPedido
public class PItemPedido {

    // Conexão com o banco de dados
    private Connection cnn;

    // Construtor que obtém a conexão com o banco de dados
    public PItemPedido() {
        cnn = Conexao.getConnection();
    }

    // Método para inserir um novo item de pedido no banco de dados
    public void inserir(EItemPedido itemPedido) {
        PreparedStatement prd = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO itempedido (qtdeitem, valorvenda, codigopedido, codigoproduto) VALUES (?, ?, ?, ?)";
            prd = cnn.prepareStatement(sql);
            prd.setInt(1, itemPedido.getQtdItem());
            prd.setDouble(2, itemPedido.getValorVenda());
            prd.setInt(3, itemPedido.getCodigoPedido().getCodigo());
            prd.setInt(4, itemPedido.getCodigoProduto().getCodigo());
            prd.executeUpdate();

            // Recuperar o código gerado manualmente, se necessário
            sql = "SELECT currval(pg_get_serial_sequence('itempedido','codigo')) AS codigo";
            prd = cnn.prepareStatement(sql);
            rs = prd.executeQuery();
            if (rs.next()) {
                int codigo = rs.getInt("codigo");
                itemPedido.setCodigo(codigo); // Define o código gerado no item do pedido
            } else {
                System.out.println("Erro ao obter o código gerado do item do pedido.");
            }
        } catch (Exception e) {
            System.out.println("Erro na inserção do itemPedido: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (prd != null) prd.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }
    }

    // Método para excluir itens de pedido com base no código do pedido
    public void excluirPorPedido(int codigoPedido) {
        try {
            String sql = "DELETE FROM itempedido WHERE codigopedido = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigoPedido);
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na exclusão do item pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para listar todos os itens de um pedido com base no código do pedido
    public List<EItemPedido> listarPorPedido(int codigoPedido) {
        List<EItemPedido> itens = new ArrayList<>();

        try {
            String sql = "SELECT * FROM itempedido WHERE codigopedido = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigoPedido);
            ResultSet rst = prd.executeQuery();

            while (rst.next()) {
                EItemPedido item = new EItemPedido();
                item.setCodigo(rst.getInt("codigo"));
                item.setQtdItem(rst.getInt("qtdeitem"));
                item.setValorVenda(rst.getDouble("valorvenda"));
                item.setCodigoPedido(new PPedido().consultar(rst.getInt("codigopedido")));
                item.setCodigoProduto(new PProduto().consultar(rst.getInt("codigoproduto")));
                itens.add(item);
            }
            rst.close();
        } catch (Exception e) {
            System.out.println("Erro na listagem de itens do pedido: " + e.getMessage());
            e.printStackTrace();
        }
        return itens;
    }
}
