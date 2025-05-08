package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Moto;

import java.util.UUID;

public class MotoDto {
    private Long id;
    private String placa;
    private String modelo;
    private String numeroChassi;
    private String cor;
    private UUID codigoBeacon;
    private String status;
    private String condicaoMecanica;


    public MotoDto() {
    }

    public MotoDto(Moto moto) {
        this.id = moto.getId();
        this.placa = moto.getPlaca();
        this.numeroChassi = moto.getNumeroChassi();
        this.modelo = moto.getModelo();
        this.cor = moto.getCor();
        this.codigoBeacon = moto.getCodigoBeacon() != null ? moto.getCodigoBeacon().getCodigoUnico() : null;
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getCodigoBeacon() {
        return codigoBeacon;
    }

    public void setCodigoBeacon(UUID codigoBeacon) {
        this.codigoBeacon = codigoBeacon;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCondicaoMecanica() {
        return condicaoMecanica;
    }

    public void setCondicaoMecanica(String condicaoMecanica) {
        this.condicaoMecanica = condicaoMecanica;
    }
}
