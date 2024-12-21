package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidade.ECliente;
import model.negocio.NCliente;

@WebServlet(name = "CCliente", urlPatterns = {"/CCliente"})
public class CCliente extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String LISTAR = "listarCliente.jsp";
    private static final String MANTER = "manterCliente.jsp";
    private NCliente negocio;
    private String proxPag;

    // Construtor que inicializa a instância de negócios e a página de redirecionamento
    public CCliente() {
        negocio = new NCliente();
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
            int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do cliente a ser excluído
            negocio.excluir(codigo); // Exclui o cliente
            listar(request, response); // Chama o método listar
        } else if (acao.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo")); // Obtém o código do cliente a ser alterado
            ECliente cliente = negocio.consultar(codigo); // Consulta o cliente
            request.setAttribute("cliente", cliente); // Define o cliente como atributo da requisição
            proxPag = MANTER; // Define a página de manutenção como próxima página
        } else if (acao.equalsIgnoreCase("incluir")) {
            ECliente cliente = new ECliente(); // Cria um novo cliente
            request.setAttribute("cliente", cliente); // Define o cliente como atributo da requisição
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
        String codigo = request.getParameter("codigo");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");

        ECliente cliente = new ECliente();
        if (codigo != null && !codigo.isEmpty()) {
            cliente.setCodigo(Integer.parseInt(codigo)); // Define o código do cliente se estiver presente
        }
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone); // Usando o método auxiliar que remove a formatação

        negocio.salvar(cliente); // Salva o cliente

        // Redirecionar para listarCliente.jsp após salvar
        response.sendRedirect("CCliente?acao=listar");
    }

    // Método para listar todos os clientes
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ECliente> lstClientes = negocio.listar(); // Obtém a lista de clientes
        request.setAttribute("lstClientes", lstClientes); // Define a lista de clientes como atributo da requisição
        proxPag = LISTAR; // Define a página de listagem como próxima página
        RequestDispatcher pagina = request.getRequestDispatcher(proxPag); // Obtém o dispatcher para a próxima página
        pagina.forward(request, response); // Redireciona para a próxima página
    }
}
