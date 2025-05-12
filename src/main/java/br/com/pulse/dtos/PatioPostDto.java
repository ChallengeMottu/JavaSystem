package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Endereco;
import br.com.pulse.domainmodel.Patio;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatioPostDto {
    private Endereco endereco;
    private double comprimento;
    private double largura;

    public PatioPostDto(Patio patio){
        this.endereco = patio.getEndereco();
        this.comprimento = patio.getComprimento();
        this.largura = patio.getLargura();
    }

}
