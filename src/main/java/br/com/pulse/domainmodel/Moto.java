package br.com.pulse.domainmodel;


import br.com.pulse.domainmodel.enuns.CondicaoMecanica;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.domainmodel.enuns.StatusBeacon;
import br.com.pulse.domainmodel.enuns.StatusMoto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Objects;

@Entity
@NoArgsConstructor
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;


    @Getter @Setter
    private String placa;

    @Getter @Setter
    private ModeloMoto modelo;

    @Getter @Setter
    private String numeroChassi;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private CondicaoMecanica condicaoMecanica;

    @Enumerated(EnumType.STRING)
    @Getter @Setter
    private StatusMoto status;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    @Getter @Setter
    private Beacon beacon;

    @ManyToOne
    @JoinColumn(name = "patio_id")
    @JsonBackReference
    @Getter @Setter
    private Patio patio;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Moto moto = (Moto) obj;
        return Objects.equals(id, moto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
