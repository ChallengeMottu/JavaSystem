package br.com.pulse.dtos.request;

import br.com.pulse.domainmodel.enums.StatusBeacon;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BeaconRequestDto {


    @NotNull(message = "Código UUID é obrigatório")
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
            message = "Código UUID inválido")
    private String beaconCode;


    @NotNull(message = "Status do beacon é obrigatório")
    private StatusBeacon beaconStatus;

    private Long motorcycleId;



    public String getBeaconCode() {
        return beaconCode;
    }

    public void setBeaconCode(String beaconCode) {
        this.beaconCode = beaconCode;
    }

    public StatusBeacon getBeaconStatus() {
        return beaconStatus;
    }

    public void setBeaconStatus(StatusBeacon beaconStatus) {
        this.beaconStatus = beaconStatus;
    }

    public Long getMotorcycleId() {
        return motorcycleId;
    }

    public void setMotorcycleId(Long motorcycleId) {
        this.motorcycleId = motorcycleId;
    }
}
