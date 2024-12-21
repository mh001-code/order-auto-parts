<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
        <title>GESTÃO DE PEDIDOS DE AUTOPEÇAS- PEDIDO</title>
        <link rel="stylesheet" href="./estilo.css">
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <br/>
        <br/>
        <br/>
        <h4>PEDIDO</h4>

        <form method="POST" action="CPedido" name="manterPedido">
            <table>
                <tr>
                    <td>Código</td>
                    <td>
                        <input type="text" readonly="readonly" name="codigo" size="10" maxlength="10" class="bloqueado" value="<c:out value='${pedido.codigo}'/>"/>
                    </td>
                </tr>

                <tr>
                    <td>Cliente</td>
                    <td>
                        <select id="codigocliente" name="codigocliente">
                            <option value="0">Selecione...</option>
                            <c:forEach var="cliente" items="${clientes}">
                                <option value="${cliente.codigo}" <c:if test="${pedido.codigocliente.codigo == cliente.codigo}">selected</c:if>>
                                    ${cliente.nome}
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td>Data do Pedido</td>
                    <td>
                        <input type="date" name="datapedido" value="<fmt:formatDate value='${pedido.datapedido}' pattern='yyyy-MM-dd'/>"/>
                    </td>
                </tr>

                <tr>
                    <td>Valor Total</td>
                    <td>
                        <input type="number" step="0.01" name="valortotal" id="valortotal" readonly class="bloqueado" value="<c:out value='${pedido.valortotal}'/>"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <h4>Itens do Pedido</h4>
                        <table id="itemPedidoTable">
                            <thead>
                                <tr>
                                    <th>Produto</th>
                                    <th>Quantidade</th>
                                    <th>Valor</th>
                                    <th>Total</th>
                                    <th>Ações</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="itempedido" items="${pedido.itempedido}">
                                    <tr>
                                        <td>
                                            <select name="codigoproduto" onchange="atualizarValorProduto(this)">
                                                <option value="0">Selecione...</option>
                                                <c:forEach var="produto" items="${produtos}">
                                                    <option value="${produto.codigo}" data-valor="${produto.valor}" <c:if test="${itempedido.codigoProduto.codigo == produto.codigo}">selected</c:if>>
                                                        ${produto.descricao}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td><input type="number" name="qtdItem" value="${itempedido.qtdItem}" oninput="calcularValorTotal()"/></td>
                                        <td><input type="number" step="0.01" name="valorVenda" value="${itempedido.valorVenda}" oninput="calcularValorTotal()"/></td>
                                        <td><input type="text" name="total" value="${itempedido.qtdItem * itempedido.valorVenda}" readonly/></td>
                                        <td><button type="button" onclick="removerItem(this)">Remover</button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <button type="button" onclick="adicionarItem()">Adicionar Item</button>
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


        <script>
            // Função para adicionar um novo item ao pedido
            function adicionarItem() {
                var tabela = document.getElementById('itemPedidoTable').getElementsByTagName('tbody')[0];
                var novaLinha = tabela.insertRow();

                var celulaProduto = novaLinha.insertCell(0);
                var celulaQuantidade = novaLinha.insertCell(1);
                var celulaValor = novaLinha.insertCell(2);
                var celulaTotal = novaLinha.insertCell(3);
                var celulaAcoes = novaLinha.insertCell(4);

                // Adiciona a seleção de produto dinamicamente
                var selectProduto = document.createElement("select");
                selectProduto.name = "codigoproduto";
                selectProduto.onchange = function () {
                    atualizarValorProduto(this);
                };

                // Adicionar a opção "Selecione..."
                var optionSelecione = document.createElement("option");
                optionSelecione.value = "0";
                optionSelecione.text = "Selecione...";
                selectProduto.appendChild(optionSelecione);

                // Obter os produtos do campo de seleção existente e adicionar opções
            <c:forEach var="produto" items="${produtos}">
                var option = document.createElement("option");
                option.value = "${produto.codigo}";
                option.text = "${produto.descricao}";
                option.setAttribute("data-valor", "${produto.valor}");
                selectProduto.appendChild(option);
            </c:forEach>

                celulaProduto.appendChild(selectProduto);
                celulaQuantidade.innerHTML = '<input type="number" name="qtdItem" value="1" oninput="calcularValorTotal()"/>';
                celulaValor.innerHTML = '<input type="number" step="0.01" name="valorVenda" value="0" oninput="calcularValorTotal()"/>';
                celulaTotal.innerHTML = '<input type="text" name="total" value="0" readonly/>';
                celulaAcoes.innerHTML = '<button type="button" onclick="removerItem(this)">Remover</button>';
            }

            // Função para atualizar o valor do produto selecionado
            function atualizarValorProduto(select) {
                var valorVendaInput = select.parentNode.nextSibling.nextSibling.firstChild;
                var selectedOption = select.options[select.selectedIndex];
                var valor = selectedOption.getAttribute("data-valor");
                if (valor) {
                    valorVendaInput.value = parseFloat(valor).toFixed(2);
                } else {
                    valorVendaInput.value = "0";
                }
                calcularValorTotal();
            }

            // Função para remover um item do pedido
            function removerItem(botao) {
                var linha = botao.parentNode.parentNode;
                linha.parentNode.removeChild(linha);
                calcularValorTotal();
            }

            // Função para calcular o valor total do pedido
            function calcularValorTotal() {
                var total = 0;
                var linhas = document.getElementById('itemPedidoTable').getElementsByTagName('tbody')[0].getElementsByTagName('tr');
                for (var i = 0; i < linhas.length; i++) {
                    var qtd = parseFloat(linhas[i].getElementsByTagName('input')[0].value);
                    var valor = parseFloat(linhas[i].getElementsByTagName('input')[1].value);
                    var subtotal = qtd * valor;
                    linhas[i].getElementsByTagName('input')[2].value = subtotal.toFixed(2);
                    total += subtotal;
                }
                document.getElementById('valortotal').value = total.toFixed(2);
            }
        </script>
    </body>
</html>
