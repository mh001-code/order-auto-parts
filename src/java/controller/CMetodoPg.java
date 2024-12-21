package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidade.EMetodoPg;
import model.negocio.NMetodoPg;

/**
 * Servlet controlador para a entidade EMetodoPg
 */
@WebServlet(name = "CMetodoPg", urlPatterns = {"/CMetodoPg"})
public class CMetodoPg extends HttpServlet {

    // Constantes com os nomes das páginas para serem usados no controlador
    private static final String LISTAR = "listarMetodoPg.jsp";
    private static final String MANTER = "manterMetodoPg.jsp";

    // Instância da camada de negócios
    private NMetodoPg negocio;
    private String proxPag;

    // Construtor do controlador que inicializa a instância de negócios
    public CMetodoPg() {
        negocio = new NMetodoPg();
        proxPag = "";
    }

    /**
     * Método para lidar com requisições HTTP <code>GET</code>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se ocorrer um erro específico do servlet
     * @throws IOException se ocorrer um erro de I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Variável de controle da página passada na URL do sistema
        String acao = request.getParameter("acao");

        // Condições para os valores possíveis da variável 'acao'
        if (acao.equalsIgnoreCase("listar")) {
            listar(request, response);
        } else if (acao.equalsIgnoreCase("excluir")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            negocio.excluir(codigo);
            listar(request, response);
        } else if (acao.equalsIgnoreCase("alterar")) {
            int codigo = Integer.parseInt(request.getParameter("codigo"));
            EMetodoPg metodopg = negocio.consultar(codigo);
            request.setAttribute("metodopg", metodopg);
            proxPag = MANTER;
        } else if (acao.equalsIgnoreCase("incluir")) {
            EMetodoPg metodopg = new EMetodoPg();
            request.setAttribute("metodopg", metodopg);
            proxPag = MANTER;
        } else {
            // Por default, se a ação não corresponder a nenhuma das anteriores
            // o comportamento do sistema será ir para a tela de listagem
            listar(request, response);
        }

        // Constroi o objeto para navegar para a próxima tela
        RequestDispatcher pagina = request.getRequestDispatcher(proxPag);
        pagina.forward(request, response);
    }

    /**
     * Método para lidar com requisições HTTP <code>POST</code>
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException se ocorrer um erro específico do servlet
     * @throws IOException se ocorrer um erro de I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String codigo = request.getParameter("codigo");
        String descricao = request.getParameter("descricao");

        EMetodoPg metodopg = new EMetodoPg();
        metodopg.setCodigo(Integer.parseInt(codigo));
        metodopg.setDescricao(descricao);

        negocio.salvar(metodopg);

        listar(request, response);

        // Constroi o objeto para navegar para a próxima tela
        RequestDispatcher pagina = request.getRequestDispatcher(proxPag);
        pagina.forward(request, response);
    }

    // Método para listar todos os métodos de pagamento
    private void listar(HttpServletRequest request, HttpServletResponse response) {
        List<EMetodoPg> lstMetodos = negocio.listar();
        request.setAttribute("lstMetodos", lstMetodos);
        proxPag = LISTAR;
    }
}
