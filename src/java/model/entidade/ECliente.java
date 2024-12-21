package model.entidade;

// Classe representando a entidade Cliente
public class ECliente {

    // Atributos privados da classe
    private int codigo;        // Código único do cliente
    private String nome;       // Nome do cliente
    private String endereco;   // Endereço do cliente
    private long telefone;     // Telefone do cliente

    // Métodos getter e setter para acessar e modificar os atributos

    // Retorna o código do cliente
    public int getCodigo() {
        return codigo;
    }

    // Define o código do cliente
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    // Retorna o nome do cliente
    public String getNome() {
        return nome;
    }

    // Define o nome do cliente
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna o endereço do cliente
    public String getEndereco() {
        return endereco;
    }

    // Define o endereço do cliente
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Retorna o telefone do cliente
    public long getTelefone() {
        return telefone;
    }

    // Define o telefone do cliente usando um long
    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    // Define o telefone do cliente usando uma String
    public void setTelefone(String telefone) {
        // Remove todos os caracteres que não são dígitos e converte para long
        this.telefone = Long.parseLong(telefone.replaceAll("[^\\d]", ""));
    }

    // Retorna o telefone do cliente formatado
    public String getTelefoneFormatado() {
        // Converte o telefone para String
        String telefoneStr = String.valueOf(telefone);
        // Formata a String no formato (XX) XXXXX-XXXX
        return String.format("(%s) %s-%s",
                telefoneStr.substring(0, 2),   // DDD
                telefoneStr.substring(2, 7),   // Primeira parte do número
                telefoneStr.substring(7));     // Segunda parte do número
    }
}
