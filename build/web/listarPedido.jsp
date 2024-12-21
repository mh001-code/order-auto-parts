<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="./estilo.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous" >

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS-PEDIDOS</title>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <br/>
        <div class="container">
            <h4>Lista de Pedidos</h4>

            <form name="listarPedido" action="CPedido" method="GET">
                <div class="table-responsive-sm mb-3">
                    <table class="table table-bordered table-hover table-sm" style="width:100%">
                        <thead class="thead-dark">
                            <tr>                            
                                <th>Código</th>
                                <th>Cliente</th>
                                <th>Data do Pedido</th>
                                <th>Valor Total</th>
                                <th colspan="2" align="center">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lstPedidos}" var="pedido">
                                <tr>
                                    <td>${pedido.codigo}</td>
                                    <td>${pedido.codigocliente.nome}</td> <!-- Acesso ao nome do cliente -->
                                    <td><fmt:formatDate value="${pedido.datapedido}" pattern="dd/MM/yyyy"/></td>
                                    <td>${pedido.valortotal}</td>
                                    <td><a href="CPedido?acao=alterar&codigo=<c:out value='${pedido.codigo}' />" class="btn btn-warning btn-sm">Alterar</a></td>
                                    <td><a href="CPedido?acao=excluir&codigo=<c:out value='${pedido.codigo}' />" 
                                           onClick="return confirm('Deseja realmente excluir o pedido?')" class="btn btn-danger btn-sm">Excluir</a></td>
                                </tr>
                            </c:forEach>

                        </tbody>
                        <tfoot class="table-primary">
                            <tr>
                                <td align="center" colspan="6">
                                    <a href="CPedido?acao=incluir" class="btn btn-success">Novo Pedido</a>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </form>
        </div>
    </body>
</html>
