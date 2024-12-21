package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidade.EItemPedido;
import model.negocio.NItemPedido;

@WebServlet(name = "CItemPedido", urlPatterns = {"/CItemPedido"})
public class CItemPedido extends HttpServlet {

    private static final String LISTAR = "listarItemPedido.jsp";
    private static final String MANTER = "manterItemPedido.jsp";

    private NItemPedido negocio;
    private String proxPag;

    // Construtor que inicializa a instância de negócios e a página de redirecionamento
    public CItemPedido() {
        negocio = new NItemPedido();
        proxPag = "";
    }

    // Método doGet para lidar com as requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao"); // Obtém a ação da requisição

        if (acao.equalsIgnoreCase("listarPorPedido")) {
            int codigoPedido = Integer.parseInt(request.getParameter("codigoPedido")); // Obtém o código do pedido
            listarPorPedido(request, response, codigoPedido); // Chama o método listarPorPedido
        } else if (acao.equalsIgnoreCase("excluirPorPedido")) {
            int codigoPedido = Integer.parseInt(request.getParameter("codigoPedido")); // Obtém o código do pedido
            excluirPorPedido(request, response, codigoPedido); // Chama o método excluirPorPedido
        } else {
            proxPag = LISTAR; // Define a página de listagem como próxima página
        }

        RequestDispatcher pagina = request.getRequestDispatcher(proxPag); // Obtém o dispatcher para a próxima página
        pagina.forward(request, response); // Redireciona para a próxima página
    }

    // Método doPost para lidar com as requisições POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Aqui pode ser adicionada lógica para lidar com submissões de formulários, se necessário
    }

    // Método para listar itens de pedido
    private void listar(HttpServletRequest request, HttpServletResponse response, int codigoPedido) {
        List<EItemPedido> lstItensPedido = negocio.listarPorPedido(codigoPedido); // Obtém a lista de itens do pedido
        request.setAttribute("lstItensPedido", lstItensPedido); // Define a lista de itens como atributo da requisição
        proxPag = LISTAR; // Define a página de listagem como próxima página
    }

    // Método para listar itens de pedido por pedido específico
    private void listarPorPedido(HttpServletRequest request, HttpServletResponse response, int codigoPedido) {
        List<EItemPedido> lstItensPedido = negocio.listarPorPedido(codigoPedido); // Obtém a lista de itens do pedido
        request.setAttribute("lstItensPedido", lstItensPedido); // Define a lista de itens como atributo da requisição
        proxPag = LISTAR; // Define a página de listagem como próxima página
    }

    // Método para excluir itens de pedido por pedido específico
    private void excluirPorPedido(HttpServletRequest request, HttpServletResponse response, int codigoPedido) {
        negocio.excluirPorPedido(codigoPedido); // Exclui itens do pedido
        proxPag = LISTAR; // Define a página de listagem como próxima página
    }
}
