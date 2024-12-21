package model.entidade;

// Classe representando a entidade Item de Pedido
public class EItemPedido {

    // Atributos privados da classe
    private int codigo;            // Código único do item do pedido
    private int qtdItem;           // Quantidade de itens no pedido
    private double valorVenda;     // Valor de venda do item
    private EPedido codigoPedido;  // Referência ao pedido associado
    private EProduto codigoProduto;// Referência ao produto associado

    // Métodos getter e setter para acessar e modificar os atributos

    // Retorna o código do item do pedido
    public int getCodigo() {
        return codigo;
    }

    // Define o código do item do pedido
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna a quantidade de itens no pedido
    public int getQtdItem() {
        return qtdItem;
    }

    // Define a quantidade de itens no pedido
    public void setQtdItem(int qtdItem) {
        this.qtdItem = qtdItem;
    }

    // Retorna o valor de venda do item
    public double getValorVenda() {
        return valorVenda;
    }

    // Define o valor de venda do item
    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }

    // Retorna o pedido associado ao item
    public EPedido getCodigoPedido() {
        return codigoPedido;
    }

    // Define o pedido associado ao item
    public void setCodigoPedido(EPedido codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    // Retorna o produto associado ao item
    public EProduto getCodigoProduto() {
        return codigoProduto;
    }

    // Define o produto associado ao item
    public void setCodigoProduto(EProduto codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
}
