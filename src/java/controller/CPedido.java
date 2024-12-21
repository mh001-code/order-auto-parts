package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidade.ECliente;
import model.entidade.EItemPedido;
import model.entidade.EPedido;
import model.entidade.EPagamento;
import model.entidade.EProduto;
import model.negocio.NCliente;
import model.negocio.NPedido;
import model.negocio.NPagamento;
import model.negocio.NProduto;

@WebServlet(name = "CPedido", urlPatterns = {"/CPedido"})
public class CPedido extends HttpServlet {

    private static final String LISTAR = "listarPedido.jsp";
    private static final String MANTER = "manterPedido.jsp";
    private static final String PAGAR = "manterPagamento.jsp";

    private NPedido negocio;
    private String proxPag;

    // Construtor que inicializa a instância de negócios e a página de redirecionamento
    public CPedido() {
        negocio = new NPedido();
        proxPag = "";
    }

    // Método doGet para lidar com as requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao"); // Obtém a ação da requisição
        Logger.getLogger(CPedido.class.getName()).log(Level.INFO, "Ação recebida no GET: {0}", acao);

        try {
            if (acao.equalsIgnoreCase("listar")) {
                listar(request, response); // Chama o método listar
            } else if (acao.equalsIgnoreCase("excluir")) {
                int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do pedido a ser excluído
                Logger.getLogger(CPedido.class.getName()).log(Level.INFO, "Excluindo pedido com código: {0}", codigo);
                negocio.excluir(codigo); // Exclui o pedido
                listar(request, response); // Chama o método listar
            } else if (acao.equalsIgnoreCase("alterar")) {
                int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do pedido a ser alterado
                EPedido pedido = negocio.consultar(codigo); // Consulta o pedido
                request.setAttribute("pedido", pedido); // Define o pedido como atributo da requisição
                request.setAttribute("clientes", new NCliente().listar()); // Define a lista de clientes como atributo
                request.setAttribute("produtos", new NProduto().listar()); // Define a lista de produtos como atributo
                proxPag = MANTER; // Define a página de manutenção como próxima página
            } else if (acao.equalsIgnoreCase("incluir")) {
                EPedido pedido = new EPedido(); // Cria um novo pedido
                request.setAttribute("pedido", pedido); // Define o pedido como atributo da requisição
                request.setAttribute("clientes", new NCliente().listar()); // Define a lista de clientes como atributo
                request.setAttribute("produtos", new NProduto().listar()); // Define a lista de produtos como atributo
                proxPag = MANTER; // Define a página de manutenção como próxima página
            } else {
                listar(request, response); // Chama o método listar
            }

            RequestDispatcher pagina = request.getRequestDispatcher(proxPag); // Obtém o dispatcher para a próxima página
            pagina.forward(request, response); // Redireciona para a próxima página
        } catch (Exception ex) {
            Logger.getLogger(CPedido.class.getName()).log(Level.SEVERE, "Erro no processamento do GET: {0}", ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Método doPost para lidar com as requisições POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Logger.getLogger(CPedido.class.getName()).log(Level.INFO, "Iniciando processamento do POST");

        String codigo = request.getParameter("codigo");
        String datapedido = request.getParameter("datapedido");
        String codigocliente = request.getParameter("codigocliente");

        Logger.getLogger(CPedido.class.getName()).log(Level.INFO, "Parâmetros recebidos: codigo={0}, datapedido={1}, codigocliente={2}", new Object[]{codigo, datapedido, codigocliente});

        EPedido pedido = new EPedido();
        pedido.setCodigo(codigo == null || codigo.isEmpty() ? 0 : Integer.parseInt(codigo)); // Define o código do pedido
        pedido.setDatapedido(Date.valueOf(datapedido)); // Define a data do pedido
       
        ECliente cliente = new ECliente();
        cliente.setCodigo(Integer.parseInt(codigocliente)); // Define o código do cliente
        pedido.setCodigocliente(cliente); // Associa o cliente ao pedido

        List<EItemPedido> itensPedido = new ArrayList<>();
        String[] codigosProdutos = request.getParameterValues("codigoproduto");
        String[] quantidades = request.getParameterValues("qtdItem");
        String[] valores = request.getParameterValues("valorVenda");

        Logger.getLogger(CPedido.class.getName()).log(Level.INFO, "Itens do pedido recebidos: codigosProdutos={0}, quantidades={1}, valores={2}", new Object[]{codigosProdutos, quantidades, valores});

        double valorTotal = 0.0;
        if (codigosProdutos != null) {
            for (int i = 0; i < codigosProdutos.length; i++) {
                EItemPedido item = new EItemPedido();
                EProduto produto = new EProduto();
                produto.setCodigo(Integer.parseInt(codigosProdutos[i])); // Define o código do produto
                item.setCodigoProduto(produto);
                item.setQtdItem(Integer.parseInt(quantidades[i])); // Define a quantidade do item
                item.setValorVenda(Double.parseDouble(valores[i])); // Define o valor de venda do item
                itensPedido.add(item);
                valorTotal += item.getQtdItem() * item.getValorVenda(); // Calcula o valor total do pedido
            }
        }

        pedido.setItempedido(itensPedido); // Associa os itens ao pedido
        pedido.setValortotal(valorTotal); // Define o valor total calculado

        try {
            negocio.salvar(pedido); // Salva o pedido
            Logger.getLogger(CPedido.class.getName()).log(Level.INFO, "Pedido salvo com sucesso: {0}", pedido);
            // Redireciona para a página de pagamento
            response.sendRedirect("CPagamento?acao=incluir&codigoPedido=" + pedido.getCodigo());
        } catch (Exception ex) {
            Logger.getLogger(CPedido.class.getName()).log(Level.SEVERE, "Erro ao salvar o pedido: {0}", ex.getMessage());
            ex.printStackTrace();
            listar(request, response); // Chama o método listar em caso de erro
        }
    }

    // Método para listar todos os pedidos
    private void listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<EPedido> lstPedidos = negocio.listar(); // Obtém a lista de pedidos
            Map<Integer, EPagamento> pagamentosPorPedido = new HashMap<>();

            NPagamento nPagamento = new NPagamento();
            for (EPedido pedido : lstPedidos) {
                int codigoCliente = pedido.getCodigocliente().getCodigo();
                ECliente cliente = new NCliente().consultar(codigoCliente); // Consulta o cliente
                pedido.setCodigocliente(cliente); // Associa o cliente completo ao pedido

                // Obter pagamento associado ao pedido
                EPagamento pagamento = nPagamento.consultar(pedido.getCodigo());
                if (pagamento != null) {
                    pagamentosPorPedido.put(pedido.getCodigo(), pagamento); // Associa o pagamento ao pedido
                }
            }

            request.setAttribute("lstPedidos", lstPedidos); // Define a lista de pedidos como atributo da requisição
            request.setAttribute("pagamentosPorPedido", pagamentosPorPedido); // Define os pagamentos por pedido como atributo
            proxPag = LISTAR; // Define a página de listagem como próxima página
            Logger.getLogger(CPedido.class.getName()).log(Level.INFO, "Listando pedidos, total: {0}", lstPedidos.size());
        } catch (Exception ex) {
            Logger.getLogger(CPedido.class.getName()).log(Level.SEVERE, "Erro ao listar pedidos: {0}", ex.getMessage());
            ex.printStackTrace();
        }
    }
}
