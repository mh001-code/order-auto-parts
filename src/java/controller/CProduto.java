package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidade.EProduto;
import model.negocio.NProduto;

@WebServlet(name = "CProduto", urlPatterns = {"/CProduto"})
public class CProduto extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String LISTAR = "listarProduto.jsp";
    private static final String MANTER = "manterProduto.jsp";
    private NProduto negocio;
    private String proxPag;

    // Construtor que inicializa a instância de negócios e a página de redirecionamento
    public CProduto() {
        negocio = new NProduto();
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
            int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do produto a ser excluído
            negocio.excluir(codigo); // Exclui o produto
            listar(request, response); // Chama o método listar
        } else if (acao.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do produto a ser alterado
            EProduto produto = negocio.consultar(codigo); // Consulta o produto
            request.setAttribute("produto", produto); // Define o produto como atributo da requisição
            proxPag = MANTER; // Define a página de manutenção como próxima página
        } else if (acao.equalsIgnoreCase("incluir")) {
            EProduto produto = new EProduto(); // Cria um novo produto
            request.setAttribute("produto", produto); // Define o produto como atributo da requisição
            proxPag = MANTER; // Define a página de manutenção como próxima página
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String codigo = request.getParameter("codigo");
        String descricao = request.getParameter("descricao");
        String qtde = request.getParameter("qtde");
        String valor = request.getParameter("valor");
        String disp = request.getParameter("disp");

        EProduto produto = new EProduto();
        if (codigo != null && !codigo.isEmpty()) {
            produto.setCodigo(Integer.parseInt(codigo)); // Define o código do produto se estiver presente
        }
        produto.setDescricao(descricao); // Define a descrição do produto
        produto.setQtde(Integer.parseInt(qtde)); // Define a quantidade do produto
        produto.setValor(Double.parseDouble(valor)); // Define o valor do produto
        produto.setDisp(disp != null && disp.equals("on")); // Define a disponibilidade do produto

        negocio.salvar(produto); // Salva o produto
        listar(request, response); // Chama o método listar
        RequestDispatcher pagina = request.getRequestDispatcher(proxPag); // Obtém o dispatcher para a próxima página
        pagina.forward(request, response); // Redireciona para a próxima página
    }

    // Método para listar todos os produtos
    private void listar(HttpServletRequest request, HttpServletResponse response) {
        List<EProduto> lstProdutos = negocio.listar(); // Obtém a lista de produtos
        request.setAttribute("lstProdutos", lstProdutos); // Define a lista de produtos como atributo da requisição
        proxPag = LISTAR; // Define a página de listagem como próxima página
    }
}
