package br.com.pulse.dtos.request;

import br.com.pulse.domainmodel.enums.MotorcycleModel;
import br.com.pulse.domainmodel.enums.OperationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MotorcycleRequestDto {

    @NotBlank(message = "Placa é obrigatória")
    @Size(min = 7, max = 8, message = "Placa deve ter entre 7 e 8 caracteres")
    private String licensePlate;

    @NotNull(message = "Modelo é obrigatório")
    private MotorcycleModel model;

    @NotBlank(message = "Número do chassi é obrigatório")
    @Size(min = 17, max = 17, message = "Número do chassi deve ter 17 caracteres")
    private String chassisNumber;

    @NotNull(message = "Status operacional é obrigatório")
    private OperationStatus operationalStatus;

    @NotNull(message = "Condição mecânica é obrigatória")
    private String mechanicalCondition;


    @NotNull(message = "A identificação do Parking é obrigatória")
    private Long parkingId;

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