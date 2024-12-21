<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="./estilo.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous" >

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS-PAGAMENTOS</title>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <br/>
        <h4>Lista de Pagamentos</h4>

        <form name="listarPagamento" action="CPagamento" method="GET">
            <div class="table-responsive-sm mb-3">
                <table class="table table-bordered table-hover table-sm" style="width:100%">
                    <thead class="thead-dark">
                        <tr>                            
                            <th>Código</th>
                            <th>Método de Pagamento</th>
                            <th>Código do Pedido</th>
                            <th>Valor Total</th>
                            <th colspan="2" align="center">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstPagamentos}" var="pagamento">
                            <tr>
                                <td><c:out value="${pagamento.codigo}" /></td>
                                <td><c:out value="${pagamento.metodopg.descricao}" /></td>
                                <td><c:out value="${pagamento.codigoPedido.codigo}" /></td>
                                <td><c:out value="${pagamento.valorTotal}" /></td>
                                <td><a href="CPagamento?acao=alterar&codigo=<c:out value="${pagamento.codigo}" />" class="btn btn-warning btn-sm">Alterar</a></td>
                                <td><a href="CPagamento?acao=excluir&codigo=<c:out value="${pagamento.codigo}" />" 
                                       onClick="return confirm('Deseja realmente excluir o pagamento?')" class="btn btn-danger btn-sm">Excluir</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot class="table-primary">
                        <td align="center" colspan="6">
                            <a href="CPagamento?acao=incluir" class="btn btn-success">Novo Pagamento</a>
                        </td>
                    </tfoot>
                </table>
            </div>
        </form>

    </body>
</html>
