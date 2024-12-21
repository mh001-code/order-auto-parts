package model.entidade;

// Classe representando a entidade Método de Pagamento
public class EMetodoPg {
    
    // Atributos privados da classe
    private int codigo;        // Código único do método de pagamento
    private String descricao;  // Descrição do método de pagamento

    // Métodos getter e setter para acessar e modificar os atributos

    // Retorna o código do método de pagamento
    public int getCodigo() {
        return codigo;
    }

    // Define o código do método de pagamento
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna a descrição do método de pagamento
    public String getDescricao() {
        return descricao;
    }

    // Define a descrição do método de pagamento
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
