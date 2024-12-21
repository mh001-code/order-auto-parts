<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="./estilo.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous" >

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS- PRODUTOS</title>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <br/>
        <h4>Lista de Produtos</h4>

        <form name="listarProduto" action="CProduto" method="GET">
            <div class="table-responsive-sm mb-3">
                <table class="table table-bordered table-hover table-sm" style="width:100%">
                    <thead class="thead-dark">
                        <tr>                            
                            <th>Código</th>
                            <th>Descrição</th>
                            <th>Quantidade</th>
                            <th>Valor</th>
                            <th>Disponível</th>
                            <th colspan="2" align="center">Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstProdutos}" var="produto">
                            <tr>
                                <td><c:out value="${produto.codigo}" /></td>
                                <td><c:out value="${produto.descricao}" /></td>
                                <td><c:out value="${produto.qtde}" /></td>
                                <td><c:out value="${produto.valor}" /></td>
                                <td><c:out value="${produto.disp}" /></td>
                                <td><a href="CProduto?acao=alterar&codigo=<c:out value="${produto.codigo}" />" class="btn btn-warning btn-sm">Alterar</a></td>
                                <td><a href="CProduto?acao=excluir&codigo=<c:out value="${produto.codigo}" />" 
                                       onClick="return confirm('Deseja realmente excluir o produto?')" class="btn btn-danger btn-sm">Excluir</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot class="table-primary">
                        <tr>
                            <td align="center" colspan="7">
                                <a href="CProduto?acao=incluir" class="btn btn-success">Novo Produto</a>
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </form>

    </body>
</html>
