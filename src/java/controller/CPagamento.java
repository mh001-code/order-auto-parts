package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidade.EPagamento;
import model.entidade.EMetodoPg;
import model.entidade.EPedido;
import model.negocio.NMetodoPg;
import model.negocio.NPagamento;
import model.negocio.NPedido;

@WebServlet(name = "CPagamento", urlPatterns = {"/CPagamento"})
public class CPagamento extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String LISTAR = "listarPagamento.jsp";
    private static final String MANTER = "manterPagamento.jsp";
    private NPagamento negocio;
    private String proxPag;

    // Construtor que inicializa a instância de negócios e a página de redirecionamento
    public CPagamento() {
        negocio = new NPagamento();
        proxPag = "";
    }

    // Método doGet para lidar com as requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao"); // Obtém a ação da requisição
        if (acao.equalsIgnoreCase("listar")) {
            listar(request, response); // Chama o método listar
        } else if (acao.equalsIgnoreCase("excluir")) {
            int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do pagamento a ser excluído
            negocio.excluir(codigo); // Exclui o pagamento
            listar(request, response); // Chama o método listar
        } else if (acao.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do pagamento a ser alterado
            EPagamento pagamento = negocio.consultar(codigo); // Consulta o pagamento
            request.setAttribute("pagamento", pagamento); // Define o pagamento como atributo da requisição
            request.setAttribute("metodos", new NMetodoPg().listar()); // Define a lista de métodos de pagamento como atributo
            request.setAttribute("pedidos", new NPedido().listar()); // Define a lista de pedidos como atributo
            proxPag = MANTER; // Define a página de manutenção como próxima página
        } else if (acao.equalsIgnoreCase("incluir")) {
            EPagamento pagamento = new EPagamento(); // Cria um novo pagamento
            request.setAttribute("pagamento", pagamento); // Define o pagamento como atributo da requisição
            request.setAttribute("metodos", new NMetodoPg().listar()); // Define a lista de métodos de pagamento como atributo
            request.setAttribute("pedidos", new NPedido().listar()); // Define a lista de pedidos como atributo
            proxPag = MANTER; // Define a página de manutenção como próxima página
        } else if (acao.equalsIgnoreCase("obterValorTotal")) {
            int codigoPedido = Integer.parseInt(request.getParameter("codigoPedido")); // Obtém o código do pedido
            EPedido pedido = new NPedido().consultar(codigoPedido); // Consulta o pedido
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf(pedido.getValortotal())); // Retorna o valor total do pedido
            return;
        } else {
            listar(request, response); // Chama o método listar
        }
        RequestDispatcher pagina = request.getRequestDispatcher(proxPag); // Obtém o dispatcher para a próxima página
        pagina.forward(request, response); // Redireciona para a próxima página
    }

    // Método doPost para lidar com as requisições POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        String valorTotal = request.getParameter("valorTotal");
        String metodopgId = request.getParameter("metodopg");
        String codigoPedido = request.getParameter("codigoPedido");

        EPagamento pagamento = new EPagamento();
        if (codigo != null && !codigo.isEmpty()) {
            pagamento.setCodigo(Integer.parseInt(codigo)); // Define o código do pagamento se estiver presente
        }

        pagamento.setValorTotal(Double.parseDouble(valorTotal)); // Define o valor total do pagamento

        EMetodoPg metodoPg = new EMetodoPg();
        metodoPg.setCodigo(Integer.parseInt(metodopgId)); // Define o método de pagamento
        pagamento.setMetodopg(metodoPg);

        EPedido pedido = new EPedido();
        pedido.setCodigo(Integer.parseInt(codigoPedido)); // Define o código do pedido
        pagamento.setCodigoPedido(pedido);

        try {
            negocio.salvar(pagamento); // Salva o pagamento
        } catch (Exception ex) {
            Logger.getLogger(CPagamento.class.getName()).log(Level.SEVERE, null, ex); // Log de erro
        }
        listar(request, response); // Chama o método listar

        RequestDispatcher pagina = request.getRequestDispatcher(proxPag); // Obtém o dispatcher para a próxima página
        pagina.forward(request, response); // Redireciona para a próxima página
    }

    // Método para listar todos os pagamentos
    private void listar(HttpServletRequest request, HttpServletResponse response) {
        List<EPagamento> lstPagamentos = negocio.listar(); // Obtém a lista de pagamentos
        request.setAttribute("lstPagamentos", lstPagamentos); // Define a lista de pagamentos como atributo da requisição
        proxPag = LISTAR; // Define a página de listagem como próxima página
    }
}
