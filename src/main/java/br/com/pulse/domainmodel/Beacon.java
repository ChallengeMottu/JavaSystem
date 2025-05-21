package br.com.pulse.domainmodel;

import br.com.pulse.domainmodel.enuns.StatusBeacon;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(unique = true, nullable = false)
    @Getter @Setter
    private UUID codigo;


    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private StatusBeacon status;

    @OneToOne
    @JoinColumn(name = "moto_id")
    @JsonManagedReference
    @Getter @Setter
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


}
