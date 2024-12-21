package model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entidade.EProduto;
import model.util.Conexao;

// Classe responsável pela persistência de dados da entidade Produto
public class PProduto {
    
    // Conexão com o banco de dados
    private Connection cnn;

    // Construtor que obtém a conexão com o banco de dados
    public PProduto() {
        cnn = Conexao.getConnection();
    }

    // Função para inserir um novo produto no banco de dados
    public void inserir(EProduto produto) {
        try {
            String sql = "INSERT INTO produto (descricao, qtde, valor, disp) VALUES (?, ?, ?, ?)";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, produto.getDescricao());
            prd.setInt(2, produto.getQtde());
            prd.setDouble(3, produto.getValor());
            prd.setBoolean(4, produto.isDisp());
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na inserção do produto: " + e.getMessage());
        }
    }

    // Função para alterar os dados de um produto existente no banco de dados
    public void alterar(EProduto produto) {
        try {
            String sql = "UPDATE produto SET descricao = ?, qtde = ?, valor = ?, disp = ? WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, produto.getDescricao());
            prd.setInt(2, produto.getQtde());
            prd.setDouble(3, produto.getValor());
            prd.setBoolean(4, produto.isDisp());
            prd.setInt(5, produto.getCodigo());
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na alteração do produto: " + e.getMessage());
        }
    }

    // Função para excluir um produto do banco de dados com base no código
    public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM produto WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na exclusão do produto: " + e.getMessage());
        }
    }

    // Função para consultar um produto no banco de dados com base no código
    public EProduto consultar(int codigo) {
        EProduto produto = new EProduto();
        try {
            String sql = "SELECT * FROM produto WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            ResultSet rst = prd.executeQuery();
            if (rst.next()) {
                produto.setCodigo(codigo);
                produto.setDescricao(rst.getString("descricao"));
                produto.setQtde(rst.getInt("qtde"));
                produto.setValor(rst.getDouble("valor"));
                produto.setDisp(rst.getBoolean("disp"));
            }
            rst.close();
        } catch (Exception e) {
            System.out.println("Erro na consulta do produto: " + e.getMessage());
        }
        return produto;
    }

    // Função para listar todos os produtos do banco de dados
    public List<EProduto> listar() {
        List<EProduto> produtos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM produto ORDER BY descricao";
            Statement prd = cnn.createStatement();
            ResultSet rst = prd.executeQuery(sql);
            while (rst.next()) {
                EProduto produto = new EProduto();
                produto.setCodigo(rst.getInt("codigo"));
                produto.setDescricao(rst.getString("descricao"));
                produto.setQtde(rst.getInt("qtde"));
                produto.setValor(rst.getDouble("valor"));
                produto.setDisp(rst.getBoolean("disp"));
                produtos.add(produto);
            }
            rst.close();
        } catch (Exception e) {
            System.out.println("Erro na listagem de produtos: " + e.getMessage());
        }
        return produtos;
    }
}
