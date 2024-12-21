package model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entidade.EPagamento;
import model.util.Conexao;

// Classe responsável pela persistência de dados da entidade Pagamento
public class PPagamento {

    // Conexão com o banco de dados
    private Connection cnn;

    // Construtor que obtém a conexão com o banco de dados
    public PPagamento() {
        cnn = Conexao.getConnection();
    }

    // Função para inserção de pagamento no banco de dados
    public void inserir(EPagamento pagamento) {
        try {
            String sql = "INSERT INTO pagamento (metodopg, valortotal, codigopedido) VALUES (?, ?, ?)";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, pagamento.getMetodopg().getCodigo());
            prd.setDouble(2, pagamento.getValorTotal());
            prd.setInt(3, pagamento.getCodigoPedido().getCodigo());
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na inserção do pagamento: " + e.getMessage());
        }
    }
    
    // Função para alteração de pagamento no banco de dados
    public void alterar(EPagamento pagamento) {
        try {
            String sql = "UPDATE pagamento SET metodopg = ?, valortotal = ?, codigopedido = ? WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, pagamento.getMetodopg().getCodigo());
            prd.setDouble(2, pagamento.getValorTotal());
            prd.setInt(3, pagamento.getCodigoPedido().getCodigo());
            prd.setInt(4, pagamento.getCodigo());
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na alteração do pagamento: " + e.getMessage());
        }
    }

    // Função para exclusão de pagamento do banco de dados com base no código
    public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM pagamento WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na exclusão do pagamento: " + e.getMessage());
        }
    }

    // Função para consultar um pagamento no banco de dados com base no código
    public EPagamento consultar(int codigo) {
        EPagamento pagamento = new EPagamento();

        try {
            String sql = "SELECT * FROM pagamento WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {
                pagamento.setCodigo(codigo);
                pagamento.setMetodopg(new PMetodoPg().consultar(rst.getInt("metodopg")));
                pagamento.setValorTotal(rst.getDouble("valortotal"));
                pagamento.setCodigoPedido(new PPedido().consultar(rst.getInt("codigopedido")));
            }

            rst.close();
        } catch (Exception e) {
            System.out.println("Erro na consulta do pagamento: " + e.getMessage());
        }

        return pagamento;
    }

    // Função para listar todos os pagamentos do banco de dados
    public List<EPagamento> listar() {
        List<EPagamento> pagamentos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pagamento ORDER BY codigo DESC";
            Statement prd = cnn.createStatement();
            ResultSet rst = prd.executeQuery(sql);
            while (rst.next()) {
                EPagamento pagamento = new EPagamento();
                pagamento.setCodigo(rst.getInt("codigo"));
                pagamento.setMetodopg(new PMetodoPg().consultar(rst.getInt("metodopg")));
                pagamento.setValorTotal(rst.getDouble("valortotal"));
                pagamento.setCodigoPedido(new PPedido().consultar(rst.getInt("codigopedido")));
                pagamentos.add(pagamento);
            }
            rst.close();
        } catch (Exception e) {
            System.out.println("Erro na listagem de pagamentos: " + e.getMessage());
        }
        return pagamentos;
    }
}
