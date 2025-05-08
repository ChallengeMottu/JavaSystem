package br.com.pulse.domainmodel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;


@Entity
@NoArgsConstructor
public class Beacon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    private UUID codigoUnico;

    @Getter @Setter
    private boolean ehAtivo;

    @OneToOne
    @JoinColumn(name = "moto_id")
    private Moto moto;


    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Beacon beacon = (Beacon) obj;
        return Objects.equals(id, beacon.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(UUID codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public boolean isEhAtivo() {
        return ehAtivo;
    }

    public void setEhAtivo(boolean ehAtivo) {
        this.ehAtivo = ehAtivo;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}
