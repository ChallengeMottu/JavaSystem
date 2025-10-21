package br.com.pulse.dtos.response;

import br.com.pulse.domainmodel.enums.MotorcycleModel;
import br.com.pulse.domainmodel.enums.OperationStatus;

public class MotorcycleResponseDto {

    private Long id;
    private String licensePlate;
    private MotorcycleModel model;
    private String chassisNumber;
    private OperationStatus operationalStatus;
    private String mechanicalCondition;
    private Long parkingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MotorcycleModel getModel() {
        return model;
    }

    public void setModel(MotorcycleModel model) {
        this.model = model;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public OperationStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(OperationStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public String getMechanicalCondition() {
        return mechanicalCondition;
    }

    public void setMechanicalCondition(String mechanicalCondition) {
        this.mechanicalCondition = mechanicalCondition;
    }

    public Long getParkingId() {
        return parkingId;
    }

    public void setParkingId(Long parkingId) {
        this.parkingId = parkingId;
    }
}
