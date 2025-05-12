package br.com.pulse.domainmodel;

import br.com.pulse.domainmodel.enuns.StatusBeacon;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;


@Entity
@NoArgsConstructor
public class Beacon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true, nullable = false)
    private UUID codigo;


    @Enumerated(EnumType.STRING)
    private StatusBeacon status;

    @OneToOne
    @JoinColumn(name = "moto_id")
    @JsonManagedReference
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

    public UUID getCodigo() {
        return codigo;
    }

    public void setCodigo(UUID codigo) {
        this.codigo = codigo;
    }

    public StatusBeacon getStatus() {
        return status;
    }

    public void setStatus(StatusBeacon status) {
        this.status = status;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }
}
