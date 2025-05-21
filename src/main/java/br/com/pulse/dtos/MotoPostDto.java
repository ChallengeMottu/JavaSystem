package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.enuns.CondicaoMecanica;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.domainmodel.enuns.StatusMoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MotoPostDto {


    @Size(min = 7, max= 8, message = "A placa deve ter entre 7 e 8 caracteres")
    private String placa;

    @NotNull(message = "O modelo é obrigatório")
    private ModeloMoto modelo;

    @NotBlank(message = "O número de chassi é obrigatório")
    @Size(min = 17, max = 17, message = "O número de chassi precisa conter 17 caracteres")
    private String numeroChassi;


    @NotNull(message = "O status atual da moto precisa ser fornecido")
    private StatusMoto status;

    @NotNull(message = "A condição mecânica atual da moto precisa ser fornecida")
    private CondicaoMecanica condicaoMecanica;



    public MotoPostDto() {
    }

    public MotoPostDto(Moto moto) {
        this.placa = moto.getPlaca();
        this.modelo = moto.getModelo();
        this.numeroChassi = moto.getNumeroChassi();
        this.status = moto.getStatus();
        this.condicaoMecanica = moto.getCondicaoMecanica();
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
