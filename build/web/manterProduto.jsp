<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="./estilo.css">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS-PRODUTOS</title>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <br/>
        <h4>CADASTRO PRODUTO</h4>
        <form method="POST" action="CProduto" name="manterProduto">
            <table>
                <tr>
                    <td>Código</td>
                    <td>
                        <input type="text" readonly="readonly" name="codigo"
                               size="10" maxlength="10"
                               class="bloqueado" value="<c:out value="${produto.codigo}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Descrição</td>
                    <td>
                        <input type="text" name="descricao"
                               size="50" maxlength="50"
                               value="<c:out value="${produto.descricao}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Quantidade</td>
                    <td>
                        <input type="text" name="qtde"
                               size="50" maxlength="50"
                               value="<c:out value="${produto.qtde}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Valor</td>
                    <td>
                        <input type="text" name="valor"
                               size="10" maxlength="10"
                               value="<c:out value="${produto.valor}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Disponível</td>
                    <td>
                        <input type="checkbox" name="disp"
                               <c:if test="${produto.disp}">checked</c:if> />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Salvar"/>
                        <input type="button" value="Voltar" onclick="history.go(-1)"/>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
