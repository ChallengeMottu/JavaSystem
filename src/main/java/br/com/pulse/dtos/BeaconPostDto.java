package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.domainmodel.enuns.StatusBeacon;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class BeaconPostDto {

    @NotNull(message = "O status do beacon precisa ser fornecido")
    private StatusBeacon status;

    private UUID codigoBeacon;

    public BeaconPostDto() {
    }

    public BeaconPostDto(Beacon beacon) {
        this.status = beacon.getStatus();
        this.codigoBeacon = beacon.getCodigo();
    }



    public StatusBeacon getStatus() {
        return status;
    }

    public void setStatus(StatusBeacon status) {
        this.status = status;
    }

    public UUID getCodigoBeacon() {
        return codigoBeacon;
    }

    public void setCodigoBeacon(UUID codigoBeacon) {
        this.codigoBeacon = codigoBeacon;
    }
}
