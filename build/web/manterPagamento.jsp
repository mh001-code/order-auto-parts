<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="./estilo.css">

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS- DADOS DO PAGAMENTO</title>
    <script>
        // Função para atualizar o valor total com base no pedido selecionado
        function atualizarValorTotal() {
            var codigoPedido = document.getElementById("codigoPedido").value;
            if (codigoPedido != "0") {
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "CPagamento?acao=obterValorTotal&codigoPedido=" + codigoPedido, true);
                xhr.onreadystatechange = function() {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        document.getElementById("valorTotal").value = xhr.responseText;
                    }
                };
                xhr.send();
            } else {
                document.getElementById("valorTotal").value = "";
            }
        }

        // Função executada quando a página é carregada
        window.onload = function() {
            var codigoPedidoParam = new URLSearchParams(window.location.search).get("codigoPedido");
            if (codigoPedidoParam) {
                document.getElementById("codigoPedido").value = codigoPedidoParam;
                atualizarValorTotal();
            }
        };
    </script>
</head>
<body>
    <jsp:include page="menu.jsp" />
    <br/>
    <br/>
    <br/>
    <h4>DADOS DO PAGAMENTO</h4>
    <form method="POST" action="CPagamento" name="manterPagamento">
        <table>
            <tr>
                <td>Código</td>
                <td>
                    <input type="text" readonly="readonly" name="codigo"
                           size="10" maxlength="10"
                           class="bloqueado" value="<c:out value='${pagamento.codigo}'/>"/>
                </td>
            </tr>
            <tr>
                <td>Código do Pedido</td>
                <td>
                    <select id="codigoPedido" name="codigoPedido" onchange="atualizarValorTotal()">
                        <option value="0">Selecione...</option>
                        <c:forEach var="pedido" items="${pedidos}">
                            <option value="${pedido.codigo}" <c:if test="${pagamento.codigoPedido.codigo == pedido.codigo || param.codigoPedido == pedido.codigo}">selected</c:if>>
                                ${pedido.codigo}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Método de Pagamento</td>
                <td>
                    <select id="metodopg" name="metodopg">
                        <option value="0">Selecione...</option>
                        <c:forEach var="metodopg" items="${metodos}">
                            <option value="${metodopg.codigo}" <c:if test="${pagamento.metodopg.codigo == metodopg.codigo}">selected</c:if>>
                                ${metodopg.descricao}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Valor Total</td>
                <td>
                    <input type="text" id="valorTotal" readonly="readonly" name="valorTotal"
                           size="50" maxlength="50"
                           class="bloqueado" value="<c:out value='${pagamento.valorTotal}'/>"/>
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
