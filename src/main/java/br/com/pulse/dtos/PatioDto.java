package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Endereco;
import br.com.pulse.domainmodel.Patio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatioDto {

    @NotNull(message = "O endereço do patio precisa ser fornecido")
    private Endereco endereco;

    @NotBlank(message = "O comprimento em metros é obrigatório")
    private double comprimento;

    @NotBlank(message = "A largura em metros é obrigatória")
    private double largura;

    public PatioDto() {
    }

    public PatioDto(Patio patio){
        this.endereco = patio.getEndereco();
        this.comprimento = patio.getComprimento();
        this.largura = patio.getLargura();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }
}
