package br.com.pulse.domainmodel;


import br.com.pulse.domainmodel.enuns.CondicaoMecanica;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.domainmodel.enuns.StatusBeacon;
import br.com.pulse.domainmodel.enuns.StatusMoto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;



import java.util.Objects;

@Entity
@NoArgsConstructor
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    private String placa;


    private ModeloMoto modelo;




    private String numeroChassi;

    @Enumerated(EnumType.STRING)
    private CondicaoMecanica condicaoMecanica;

    @Enumerated(EnumType.STRING)
    private StatusMoto status;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    private Beacon beacon;

    @ManyToOne
    @JoinColumn(name = "patio_id")
    @JsonBackReference
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CondicaoMecanica getCondicaoMecanica() {
        return condicaoMecanica;
    }

    public void setCondicaoMecanica(CondicaoMecanica condicaoMecanica) {
        this.condicaoMecanica = condicaoMecanica;
    }

    public StatusMoto getStatus() {
        return status;
    }

    public void setStatus(StatusMoto status) {
        this.status = status;
    }

    public Beacon getBeacon() {
        return beacon;
    }

    public void setBeacon(Beacon beacon) {
        this.beacon = beacon;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }
}
