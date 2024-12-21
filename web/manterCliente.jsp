<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="./estilo.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS-CADASTRO CLIENTE</title>
        <script>
            $(document).ready(function(){
                $('input[name="telefone"]').mask('(00) 00000-0000');
            });
        </script>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <br/>
        <h4>CADASTRO CLIENTE</h4>
        <c:if test="${not empty erro}">
            <div class="alert alert-danger">${erro}</div>
        </c:if>
        <form method="POST" action="CCliente" name="manterCliente">
            <table>
                <tr>
                    <td>Código</td>
                    <td>
                        <input type="text" readonly="readonly" name="codigo"
                               size="10" maxlength="10"
                               class="bloqueado" value="<c:out value="${cliente.codigo}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Nome</td>
                    <td>
                        <input type="text" name="nome"
                               size="50" maxlength="50"
                               value="<c:out value="${cliente.nome}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Endereço</td>
                    <td>
                        <input type="text" name="endereco"
                               size="50" maxlength="50"
                               value="<c:out value="${cliente.endereco}"/>"/>
                    </td>
                </tr>
                <tr>
                    <td>Telefone</td>
                    <td>
                        <input type="text" name="telefone"
                               size="15" maxlength="15"
                               value="<c:out value="${cliente.telefone == 0 ? '' : cliente.telefoneFormatado}"/>"/>
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
