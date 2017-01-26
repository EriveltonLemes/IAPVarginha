package br.com.apptools.iapvarginha.dominio.entidades;

public class Numero {

    private Integer _idNumero;
    private int qtdBiblia;
    private int qtdEstudoDiario;
    private String dataCulto;

    //Métodos construtores
    public Numero() {

    }

    public Numero(Integer _idNumero, int qtdBiblia, int qtdEstudoDiario, String dataCulto) {
        this._idNumero = _idNumero;
        this.qtdBiblia = qtdBiblia;
        this.qtdEstudoDiario = qtdEstudoDiario;
        this.dataCulto = dataCulto;
    }

    //Get's e Set's

    public Integer get_idNumero() {
        return _idNumero;
    }

    public void set_idNumero(Integer _idNumero) {
        this._idNumero = _idNumero;
    }

    public int getQtdBiblia() {
        return qtdBiblia;
    }

    public void setQtdBiblia(int qtdBiblia) {
        this.qtdBiblia = qtdBiblia;
    }

    public int getQtdEstudoDiario() {
        return qtdEstudoDiario;
    }

    public void setQtdEstudoDiario(int qtdEstudoDiario) {
        this.qtdEstudoDiario = qtdEstudoDiario;
    }

    public String getDataCulto() {
        return dataCulto;
    }

    public void setDataCulto(String dataCulto) {
        this.dataCulto = dataCulto;
    }
}
