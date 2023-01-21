package model;

/**
 *
 * @author Usuario
 */
public class JavaBeans {

    private long id;
    private String nome;
    private String telefone;
    private String email;

    /**
     *
     * @param id
     * @param nome
     * @param telefone
     * @param email
     */
    public JavaBeans(long id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    /**
     *
     */
    public JavaBeans() {
    }

    /**
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return Telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     *
     * @param telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     *
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return toString
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JavaBeans{");
        sb.append("id=").append(id);
        sb.append(", nome=").append(nome);
        sb.append(", telefone=").append(telefone);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }

}
