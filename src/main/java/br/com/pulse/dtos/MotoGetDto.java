package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.enuns.CondicaoMecanica;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.domainmodel.enuns.StatusMoto;

import java.util.UUID;

public class MotoGetDto {
    private Long id;
    private ModeloMoto modelo;
    private String placa;
    private UUID codigoBeacon;
    private String numeroChassi;
    private PatioGetDto patio;
    private StatusMoto status;
    private CondicaoMecanica condicaoMecanica;

    public MotoGetDto() {
    }

    public MotoGetDto(Moto moto){
        this.id = moto.getId();
        this.modelo = moto.getModelo();
        this.placa = moto.getPlaca();
        this.codigoBeacon = (moto.getBeacon() != null) ? moto.getBeacon().getCodigo() : null;
        this.numeroChassi = moto.getNumeroChassi();
        this.patio = new PatioGetDto(moto.getPatio());
        this.status = moto.getStatus();
        this.condicaoMecanica = moto.getCondicaoMecanica();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModeloMoto getModelo() {
        return modelo;
    }

    public void setModelo(ModeloMoto modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public UUID getCodigoBeacon() {
        return codigoBeacon;
    }

    public void setCodigoBeacon(UUID codigoBeacon) {
        this.codigoBeacon = codigoBeacon;
    }

    public String getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }



    public PatioGetDto getPatio() {
        return patio;
    }

    public void setPatio(PatioGetDto patio) {
        this.patio = patio;
    }

    public StatusMoto getStatus() {
        return status;
    }

    public void setStatus(StatusMoto status) {
        this.status = status;
    }

    public CondicaoMecanica getCondicaoMecanica() {
        return condicaoMecanica;
    }

    public void setCondicaoMecanica(CondicaoMecanica condicaoMecanica) {
        this.condicaoMecanica = condicaoMecanica;
    }
}
