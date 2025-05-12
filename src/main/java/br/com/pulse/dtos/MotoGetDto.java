package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.repositories.MotoRepository;

import java.util.UUID;

public class MotoGetDto {
    private Long id;
    private ModeloMoto modelo;
    private String placa;
    private UUID codigoBeacon;
    private String numeroChassi;
    private String cor;

    public MotoGetDto(Moto moto){
        this.id = moto.getId();
        this.modelo = moto.getModelo();
        this.placa = moto.getPlaca();
        this.codigoBeacon = (moto.getBeacon() != null) ? moto.getBeacon().getCodigo() : null;
        this.numeroChassi = moto.getNumeroChassi();
        this.cor = moto.getCor();
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
