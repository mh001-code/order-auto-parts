<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./estilo.css">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-9">
        <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS- CADASTRO MÉTODO DE PAGAMENTO</title>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <br/>
        <h4>CADASTRO MÉTODO DE PAGAMENTO</h4>

        <form method="POST" action="CMetodoPg" name="manterMetodoPg">
            <table>
                <tr>
                    <td>Código</td>
                    <td>
                        <input type="text" readonly="readonly" name="codigo"
                               size="10" maxlength="10"
                               class="bloqueado" value="<c:out value="${metodopg.codigo}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Descrição</td>
                    <td>
                        <input type="text" name="descricao"
                               size="50" maxlength="50"
                               value="<c:out value="${metodopg.descricao}"/>"/>
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
