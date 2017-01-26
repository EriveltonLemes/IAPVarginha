package br.com.apptools.iapvarginha.dominio.entidades;

import java.util.Date;

public class Chamada {

    private Integer _idChamada;
    private Integer _idMembro;
    private String statusChamada;
    private Date dataChamada;

    //Métodos construtores
    public Chamada () {

    }

    public Chamada (Integer _idChamada, Integer _idMembro, String statusChamada, Date dataChamada){

        this._idChamada = _idChamada;
        this._idMembro = _idMembro;
        this.statusChamada = statusChamada;
        this.dataChamada = dataChamada;
    }

    //Get's e Set's

    public Integer get_idChamada() {
        return _idChamada;
    }

    public void set_idChamada(Integer _idChamada) {
        this._idChamada = _idChamada;
    }

    public Integer get_idMembro() {
        return _idMembro;
    }

    public void set_idMembro(Integer _idMembro) {
        this._idMembro = _idMembro;
    }

    public String getStatusChamada() {
        return statusChamada;
    }

    public void setStatusChamada(String statusChamada) {
        this.statusChamada = statusChamada;
    }

    public Date getDataChamada() {
        return dataChamada;
    }

    public void setDataChamada(Date dataChamada) {
        this.dataChamada = dataChamada;
    }
}
