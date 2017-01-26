package br.com.apptools.iapvarginha.dominio.entidades;

public class Usuario {

    //Declaração de variaveis

    private Integer _idUsuario;
    private String login;
    private String nomeUsuario;
    private String senha;
    private String email;

    //Métodos construtores

    public Usuario() {

    }

    public Usuario(Integer _idUsuario, String login, String nomeUsuario, String senha, String email) {

        this._idUsuario = _idUsuario;
        this.login = login;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.email = email;
    }

    //Get's e Set's


    public Integer get_idUsuario() {
        return _idUsuario;
    }

    public void set_idUsuario(Integer _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
