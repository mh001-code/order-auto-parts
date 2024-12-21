package model.entidade;

import java.util.Date;
import java.util.List;

// Classe representando a entidade Pedido
public class EPedido {

    // Atributos privados da classe
    private int codigo;                  // Código único do pedido
    private ECliente codigocliente;      // Cliente associado ao pedido
    private Date datapedido;             // Data do pedido
    private double valortotal;           // Valor total do pedido
    private List<EItemPedido> itempedido;// Lista de itens do pedido

    // Construtor padrão
    public EPedido() {}

    // Construtor que aceita código como argumento
    public EPedido(int codigo) {
        this.codigo = codigo;
    }

    // Métodos getter e setter para acessar e modificar os atributos

    // Retorna o código do pedido
    public int getCodigo() {
        return codigo;
    }

    // Define o código do pedido
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna o cliente associado ao pedido
    public ECliente getCodigocliente() {
        return codigocliente;
    }

    // Define o cliente associado ao pedido
    public void setCodigocliente(ECliente codigocliente) {
        this.codigocliente = codigocliente;
    }

    // Retorna a data do pedido
    public Date getDatapedido() {
        return datapedido;
    }

    // Define a data do pedido
    public void setDatapedido(Date datapedido) {
        this.datapedido = datapedido;
    }

    // Retorna o valor total do pedido
    public double getValortotal() {
        return valortotal;
    }

    // Define o valor total do pedido
    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    // Retorna a lista de itens do pedido
    public List<EItemPedido> getItempedido() {
        return itempedido;
    }

    // Define a lista de itens do pedido e atualiza o valor total
    public void setItempedido(List<EItemPedido> itempedido) {
        this.itempedido = itempedido;
        atualizarValorTotal(); // Atualiza o valor total ao definir os itens do pedido
    }

    // Método privado para atualizar o valor total do pedido com base nos itens
    private void atualizarValorTotal() {
        if (itempedido != null) {
            this.valortotal = itempedido.stream()
                    .mapToDouble(item -> item.getValorVenda() * item.getQtdItem())
                    .sum();
        }
    }
}
