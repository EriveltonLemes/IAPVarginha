package br.com.apptools.iapvarginha.dominio.entidades;

public class Membro {

    private Integer _idMembro;
    private String nomeMembro;
    private String sobrenomeMembro;
    private String tipoMembro;
    private String statusMembro;


    //Métodos construtores
    public Membro () {

    }

    public Membro (Integer _idMembro, String nomeMembro, String sobrenomeMembro, String tipoMembro, String statusMembro) {

        this._idMembro = _idMembro;
        this.nomeMembro = nomeMembro;
        this.sobrenomeMembro = sobrenomeMembro;
        this.tipoMembro = tipoMembro;
        this.statusMembro =statusMembro;
    }

    //Get's e Set's
    public Integer get_idMembro() {
        return _idMembro;
    }

    public void set_idMembro(Integer _idMembro) {
        this._idMembro = _idMembro;
    }

    public String getNomeMembro() {
        return nomeMembro;
    }

    public void setNomeMembro(String nomeMembro) {
        this.nomeMembro = nomeMembro;
    }

    public String getTipoMembro() {
        return tipoMembro;
    }

    public void setTipoMembro(String tipoMembro) {
        this.tipoMembro = tipoMembro;
    }

    public String getStatusMembro() {
        return statusMembro;
    }

    public void setStatusMembro(String statusMembro) {
        this.statusMembro = statusMembro;
    }

    public String getSobrenomeMembro() {
        return sobrenomeMembro;
    }

    public void setSobrenomeMembro(String sobrenomeMembro) {
        this.sobrenomeMembro = sobrenomeMembro;
    }
}