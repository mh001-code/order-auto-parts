package model.entidade;

// Classe representando a entidade Pagamento
public class EPagamento {

    // Atributos privados da classe
    private int codigo;            // Código único do pagamento
    private EMetodoPg metodopg;    // Método de pagamento associado
    private double valorTotal;     // Valor total do pagamento
    private EPedido codigoPedido;  // Pedido associado ao pagamento

    // Métodos getter e setter para acessar e modificar os atributos

    // Retorna o código do pagamento
    public int getCodigo() {
        return codigo;
    }

    // Define o código do pagamento
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna o método de pagamento associado
    public EMetodoPg getMetodopg() {
        return metodopg;
    }

    // Define o método de pagamento associado
    public void setMetodopg(EMetodoPg metodopg) {
        this.metodopg = metodopg;
    }

    // Retorna o valor total do pagamento
    public double getValorTotal() {
        return valorTotal;
    }

    // Define o valor total do pagamento
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    // Retorna o pedido associado ao pagamento
    public EPedido getCodigoPedido() {
        return codigoPedido;
    }

    // Define o pedido associado ao pagamento
    public void setCodigoPedido(EPedido codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
}
