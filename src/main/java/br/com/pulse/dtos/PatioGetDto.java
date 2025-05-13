package br.com.pulse.dtos;

import br.com.pulse.domainmodel.Endereco;
import br.com.pulse.domainmodel.Patio;

public class PatioGetDto {
    private Long id;
    private Endereco endereco;

    public PatioGetDto() {
    }

    public PatioGetDto(Patio patio) {
        this.id = patio.getId();
        this.endereco = patio.getEndereco();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
