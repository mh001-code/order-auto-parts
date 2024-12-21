package model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entidade.ECliente;
import model.util.Conexao;

// Classe responsável pela persistência de dados da entidade Cliente
public class PCliente {
    
    // Conexão com o banco de dados
    private Connection cnn;

    // Construtor que obtém a conexão com o banco de dados
    public PCliente() {
        cnn = Conexao.getConnection();
    }

    // Método para inserir um novo cliente no banco de dados
    public void inserir(ECliente cliente) {
        try {
            String sql = "INSERT INTO cliente (nome, endereco, telefone) VALUES ( ?, ?, ? )";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, cliente.getNome());
            prd.setString(2, cliente.getEndereco());
            prd.setLong(3, cliente.getTelefone());
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na inserção do cliente: " + e.getMessage());
        }
    }

    // Método para alterar os dados de um cliente existente no banco de dados
    public void alterar(ECliente cliente) {
        try {
            String sql = "UPDATE cliente SET nome = ?, endereco = ?, telefone = ? WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, cliente.getNome());
            prd.setString(2, cliente.getEndereco());
            prd.setLong(3, cliente.getTelefone());
            prd.setInt(4, cliente.getCodigo());
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na alteração do cliente: " + e.getMessage());
        }
    }

    // Método para excluir um cliente do banco de dados com base no código
    public void excluir(int codigo) {
        try {
            String sql = "DELETE FROM cliente WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            prd.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro na exclusão do cliente: " + e.getMessage());
        }
    }

    // Método para consultar um cliente no banco de dados com base no código
    public ECliente consultar(int codigo) {
        ECliente cliente = new ECliente();
        try {
            String sql = "SELECT * FROM cliente WHERE codigo = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            ResultSet rst = prd.executeQuery();
            if (rst.next()) {
                cliente.setCodigo(codigo);
                cliente.setNome(rst.getString("nome"));
                cliente.setEndereco(rst.getString("endereco"));
                cliente.setTelefone(rst.getLong("telefone"));
            }
            rst.close();
        } catch (Exception e) {
            System.out.println("Erro na consulta do cliente: " + e.getMessage());
        }
        return cliente;
    }

    // Método para listar todos os clientes do banco de dados
    public List<ECliente> listar() {
        List<ECliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cliente ORDER BY nome ";
            Statement prd = cnn.createStatement();
            ResultSet rst = prd.executeQuery(sql);
            while (rst.next()) {
                ECliente cliente = new ECliente();
                cliente.setCodigo(rst.getInt("codigo"));
                cliente.setNome(rst.getString("nome"));
                cliente.setEndereco(rst.getString("endereco"));
                cliente.setTelefone(rst.getLong("telefone"));
                clientes.add(cliente);
            }
            rst.close();
        } catch (Exception e) {
            System.out.println("Erro na listagem de clientes: " + e.getMessage());
        }
        return clientes;
    }
}
