package model.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entidade.ECliente;
import model.entidade.EItemPedido;
import model.entidade.EPedido;
import model.entidade.EProduto;
import model.util.Conexao;

// Classe responsável pela persistência de dados da entidade Pedido
public class PPedido {

    // Conexão com o banco de dados
    private Connection cnn;

    // Construtor que obtém a conexão com o banco de dados
    public PPedido() {
        cnn = Conexao.getConnection();
    }

    // Função para inserir um novo pedido no banco de dados
    public void inserir(EPedido pedido) {
        PreparedStatement prd = null;
        ResultSet rs = null;
        try {
            Logger.getLogger(PPedido.class.getName()).log(Level.INFO, "Inserindo pedido: {0}", pedido);
            // Inserção do pedido
            String sql = "INSERT INTO pedido (codigocliente, datapedido, valortotal) VALUES (?, ?, ?) RETURNING codigo";
            prd = cnn.prepareStatement(sql);
            prd.setInt(1, pedido.getCodigocliente().getCodigo());
            prd.setDate(2, new Date(pedido.getDatapedido().getTime()));
            prd.setDouble(3, pedido.getValortotal());
            rs = prd.executeQuery();
            if (rs.next()) {
                pedido.setCodigo(rs.getInt("codigo"));
            } else {
                throw new Exception("Erro ao recuperar o código gerado do pedido.");
            }

            // Inserir os itens do pedido
            inserirItensPedido(pedido);

        } catch (Exception ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao inserir pedido: {0}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (prd != null) {
                    prd.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao fechar recursos: {0}", ex.getMessage());
            }
        }
    }

    // Função para alterar um pedido existente no banco de dados
    public void alterar(EPedido pedido) {
        PreparedStatement prd = null;
        try {
            Logger.getLogger(PPedido.class.getName()).log(Level.INFO, "Alterando pedido: {0}", pedido);
            String sql = "UPDATE pedido SET codigocliente = ?, datapedido = ?, valortotal = ? WHERE codigo = ?";
            prd = cnn.prepareStatement(sql);
            prd.setInt(1, pedido.getCodigocliente().getCodigo());
            prd.setDate(2, new Date(pedido.getDatapedido().getTime()));
            prd.setDouble(3, pedido.getValortotal());
            prd.setInt(4, pedido.getCodigo());
            prd.executeUpdate();

            // Atualizar os itens do pedido
            // Excluir os itens existentes
            sql = "DELETE FROM itempedido WHERE codigopedido = ?";
            prd = cnn.prepareStatement(sql);
            prd.setInt(1, pedido.getCodigo());
            prd.executeUpdate();

            // Inserir os novos itens
            inserirItensPedido(pedido);

        } catch (Exception ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao alterar pedido: {0}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (prd != null) {
                    prd.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao fechar recursos: {0}", ex.getMessage());
            }
        }
    }

    // Função para inserir itens de um pedido no banco de dados
    private void inserirItensPedido(EPedido pedido) throws Exception {
        PreparedStatement prd = null;
        try {
            for (EItemPedido item : pedido.getItempedido()) {
                String sql = "INSERT INTO itempedido (codigoproduto, qtdeitem, valorvenda, codigopedido) VALUES (?, ?, ?, ?)";
                prd = cnn.prepareStatement(sql);
                prd.setInt(1, item.getCodigoProduto().getCodigo());
                prd.setInt(2, item.getQtdItem());
                prd.setDouble(3, item.getValorVenda());
                prd.setInt(4, pedido.getCodigo());
                prd.executeUpdate();
            }
        } finally {
            if (prd != null) {
                prd.close();
            }
        }
    }

    // Função para excluir um pedido do banco de dados com base no código
    public void excluir(int codigo) {
        PreparedStatement prd = null;
        try {
            Logger.getLogger(PPedido.class.getName()).log(Level.INFO, "Excluindo pedido com código: {0}", codigo);
            String sql = "DELETE FROM pedido WHERE codigo = ?";
            prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            prd.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao excluir pedido: {0}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (prd != null) {
                    prd.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao fechar recursos: {0}", ex.getMessage());
            }
        }
    }

    // Função para consultar um pedido no banco de dados com base no código
    public EPedido consultar(int codigo) {
        PreparedStatement prd = null;
        ResultSet rs = null;
        EPedido pedido = null;
        try {
            Logger.getLogger(PPedido.class.getName()).log(Level.INFO, "Consultando pedido com código: {0}", codigo);
            String sql = "SELECT * FROM pedido WHERE codigo = ?";
            prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            rs = prd.executeQuery();
            if (rs.next()) {
                pedido = new EPedido();
                pedido.setCodigo(rs.getInt("codigo"));
                ECliente cliente = new ECliente();
                cliente.setCodigo(rs.getInt("codigocliente"));
                pedido.setCodigocliente(cliente);
                pedido.setDatapedido(rs.getDate("datapedido"));
                pedido.setValortotal(rs.getDouble("valortotal"));

                // Consultar itens do pedido
                List<EItemPedido> itensPedido = new ArrayList<>();
                sql = "SELECT * FROM itempedido WHERE codigopedido = ?";
                prd = cnn.prepareStatement(sql);
                prd.setInt(1, codigo);
                ResultSet rsItens = prd.executeQuery();
                while (rsItens.next()) {
                    EItemPedido item = new EItemPedido();
                    EProduto produto = new EProduto();
                    produto.setCodigo(rsItens.getInt("codigoproduto"));
                    item.setCodigoProduto(produto);
                    item.setQtdItem(rsItens.getInt("qtdeitem"));
                    item.setValorVenda(rsItens.getDouble("valorvenda"));
                    itensPedido.add(item);
                }
                pedido.setItempedido(itensPedido);
            }
        } catch (Exception ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao consultar pedido: {0}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (prd != null) {
                    prd.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao fechar recursos: {0}", ex.getMessage());
            }
        }
        return pedido;
    }

    // Função para listar todos os pedidos do banco de dados
    public List<EPedido> listar() {
        PreparedStatement prd = null;
        ResultSet rs = null;
        List<EPedido> lstPedidos = new ArrayList<>();
        try {
            Logger.getLogger(PPedido.class.getName()).log(Level.INFO, "Listando todos os pedidos");
            String sql = "SELECT * FROM Pedido ORDER BY codigo DESC";
            prd = cnn.prepareStatement(sql);
            rs = prd.executeQuery();
            while (rs.next()) {
                EPedido pedido = new EPedido();
                pedido.setCodigo(rs.getInt("codigo"));
                ECliente cliente = new ECliente();
                cliente.setCodigo(rs.getInt("codigocliente"));
                pedido.setCodigocliente(cliente);
                pedido.setDatapedido(rs.getDate("datapedido"));
                pedido.setValortotal(rs.getDouble("valortotal"));

                // Consultar itens do pedido
                List<EItemPedido> itensPedido = new ArrayList<>();
                String sqlItens = "SELECT * FROM itempedido WHERE codigopedido = ?";
                PreparedStatement prdItens = cnn.prepareStatement(sqlItens);
                prdItens.setInt(1, pedido.getCodigo());
                ResultSet rsItens = prdItens.executeQuery();
                while (rsItens.next()) {
                    EItemPedido item = new EItemPedido();
                    EProduto produto = new EProduto();
                    produto.setCodigo(rsItens.getInt("codigoproduto"));
                    item.setCodigoProduto(produto);
                    item.setQtdItem(rsItens.getInt("qtdeitem"));
                    item.setValorVenda(rsItens.getDouble("valorvenda"));
                    itensPedido.add(item);
                }
                pedido.setItempedido(itensPedido);

                lstPedidos.add(pedido);
            }
        } catch (Exception ex) {
            Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao listar pedidos: {0}", ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (prd != null) {
                    prd.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                Logger.getLogger(PPedido.class.getName()).log(Level.SEVERE, "Erro ao fechar recursos: {0}", ex.getMessage());
            }
        }
        return lstPedidos;
    }
}
