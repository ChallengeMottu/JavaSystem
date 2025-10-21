package br.com.pulse.dtos.response;

import br.com.pulse.domainmodel.enums.StatusBeacon;

public class BeaconResponseDto {

    private Long id;
    private String beaconCode;
    private StatusBeacon beaconStatus;
    private Long motorcycleId;

    public BeaconResponseDto(Long id, String beaconCode, StatusBeacon beaconStatus, Long motorcycleId) {
        this.id = id;
        this.beaconCode = beaconCode;
        this.beaconStatus = beaconStatus;
        this.motorcycleId = motorcycleId;
    }

    public Long getId() {
        return id;
    }

    public String getBeaconCode() {
        return beaconCode;
    }

    public StatusBeacon getBeaconStatus() {
        return beaconStatus;
    }

    public Long getMotorcycleId() {
        return motorcycleId;
    }
}
