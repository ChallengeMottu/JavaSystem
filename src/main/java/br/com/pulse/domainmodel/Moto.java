package br.com.pulse.domainmodel;


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
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String placa;

    @Getter @Setter
    private String modelo;

    @Getter @Setter
    private String cor;

    @Getter @Setter
    private String numeroChassi;

    @Getter @Setter
    private String condicaoMecanica;

    @Getter @Setter
    private String status;

    @OneToOne(mappedBy = "moto", cascade = CascadeType.ALL)
    @Getter @Setter
    private Beacon codigoBeacon;

    @ManyToOne
    @JoinColumn(name = "patio_id")
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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getNumeroChassi() {
        return numeroChassi;
    }

    public void setNumeroChassi(String numeroChassi) {
        this.numeroChassi = numeroChassi;
    }

    public String getCondicaoMecanica() {
        return condicaoMecanica;
    }

    public void setCondicaoMecanica(String condicaoMecanica) {
        this.condicaoMecanica = condicaoMecanica;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Beacon getCodigoBeacon() {
        return codigoBeacon;
    }

    public void setCodigoBeacon(Beacon codigoBeacon) {
        this.codigoBeacon = codigoBeacon;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }
}
