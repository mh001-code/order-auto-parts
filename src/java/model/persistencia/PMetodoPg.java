package model.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entidade.EMetodoPg;
import model.util.Conexao;
import java.util.logging.Level;
import java.util.logging.Logger;

// Classe responsável pela persistência de dados da entidade MetodoPg
public class PMetodoPg {
    
    // Logger para registrar mensagens de erro
    private static final Logger logger = Logger.getLogger(PMetodoPg.class.getName());
    // Conexão com o banco de dados
    private Connection cnn;

    // Construtor que obtém a conexão com o banco de dados
    public PMetodoPg() {
        cnn = Conexao.getConnection();
    }

    // Método para inserir um novo método de pagamento no banco de dados
    public void inserir(EMetodoPg metodopg) {
        String sql = "INSERT INTO metodopg (descricao) VALUES ( ? )";
        try (PreparedStatement prd = cnn.prepareStatement(sql)) {
            prd.setString(1, metodopg.getDescricao());
            prd.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro na inserção do metodo de pagamento: ", e);
        }
    }

    // Método para alterar os dados de um método de pagamento existente no banco de dados
    public void alterar(EMetodoPg metodopg) {
        String sql = "UPDATE metodopg SET descricao = ? WHERE codigo = ?";
        try (PreparedStatement prd = cnn.prepareStatement(sql)) {
            prd.setString(1, metodopg.getDescricao());
            prd.setInt(2, metodopg.getCodigo());
            prd.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro na alteração do metodo de pagamento: ", e);
        }
    }

    // Método para excluir um método de pagamento do banco de dados com base no código
    public void excluir(int codigo) {
        String sql = "DELETE FROM metodopg WHERE codigo = ?";
        try (PreparedStatement prd = cnn.prepareStatement(sql)) {
            prd.setInt(1, codigo);
            prd.executeUpdate();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro na exclusão do metodo de pagamento: ", e);
        }
    }

    // Método para consultar um método de pagamento no banco de dados com base no código
    public EMetodoPg consultar(int codigo) {
        String sql = "SELECT * FROM metodopg WHERE codigo = ?";
        EMetodoPg metodo = new EMetodoPg();
        try (PreparedStatement prd = cnn.prepareStatement(sql)) {
            prd.setInt(1, codigo);
            try (ResultSet rst = prd.executeQuery()) {
                if (rst.next()) {
                    metodo.setCodigo(codigo);
                    metodo.setDescricao(rst.getString("descricao"));
                }
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro na consulta do metodo de pagamento: ", e);
        }
        return metodo;
    }

    // Método para listar todos os métodos de pagamento do banco de dados
    public List<EMetodoPg> listar() {
        String sql = "SELECT * FROM metodopg ORDER BY codigo DESC";
        List<EMetodoPg> metodos = new ArrayList<>();
        try (Statement stmt = cnn.createStatement(); ResultSet rst = stmt.executeQuery(sql)) {
            while (rst.next()) {
                EMetodoPg metodo = new EMetodoPg();
                metodo.setCodigo(rst.getInt("codigo"));
                metodo.setDescricao(rst.getString("descricao"));
                metodos.add(metodo);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro na listagem de metodos: ", e);
        }
        return metodos;
    }
}
