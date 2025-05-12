package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.enuns.CondicaoMecanica;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.domainmodel.enuns.StatusBeacon;

public class MotoPostDto {
    private Long id;
    private String placa;
    private ModeloMoto modelo;
    private String numeroChassi;
    private String cor;
    private StatusBeacon status;
    private CondicaoMecanica condicaoMecanica;



    public MotoPostDto() {
    }

    public MotoPostDto(Moto moto) {
        this.id = moto.getId();
        this.placa = moto.getPlaca();
        this.modelo = moto.getModelo();
        this.numeroChassi = moto.getNumeroChassi();
        this.cor = moto.getCor();
        this.status = moto.getStatus();
        this.condicaoMecanica = moto.getCondicaoMecanica();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public ModeloMoto getModelo() {
        return modelo;
    }

    public void setModelo(ModeloMoto modelo) {
        this.modelo = modelo;
    }

    public String getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public StatusBeacon getStatus() {
        return status;
    }

    public void setStatus(StatusBeacon status) {
        this.status = status;
    }


    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public CondicaoMecanica getCondicaoMecanica() {
        return condicaoMecanica;
    }

    public void setCondicaoMecanica(CondicaoMecanica condicaoMecanica) {
        this.condicaoMecanica = condicaoMecanica;
    }


}
