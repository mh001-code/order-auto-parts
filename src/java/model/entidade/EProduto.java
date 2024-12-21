package model.entidade;

// Classe representando a entidade Produto
public class EProduto {
    
    // Atributos privados da classe
    private int codigo;        // Código único do produto
    private String descricao;  // Descrição do produto
    private int qtde;          // Quantidade disponível do produto
    private double valor;      // Valor do produto
    private boolean disp;      // Indica se o produto está disponível

    // Métodos getter e setter para acessar e modificar os atributos

    // Retorna o código do produto
    public int getCodigo() {
        return codigo;
    }

    // Define o código do produto
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna a descrição do produto
    public String getDescricao() {
        return descricao;
    }

    // Define a descrição do produto
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Retorna a quantidade disponível do produto
    public int getQtde() {
        return qtde;
    }

    // Define a quantidade disponível do produto
    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    // Retorna o valor do produto
    public double getValor() {
        return valor;
    }

    // Define o valor do produto
    public void setValor(double valor) {
        this.valor = valor;
    }

    // Retorna se o produto está disponível
    public boolean isDisp() {
        return disp;
    }

    // Define se o produto está disponível
    public void setDisp(boolean disp) {
        this.disp = disp;
    }
}
