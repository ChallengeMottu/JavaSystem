package br.com.pulse.domainmodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Embedded
    @Getter @Setter
    private Endereco endereco;

    @Getter @Setter
    private double comprimento;

    @Getter @Setter
    private double largura;


    @Getter @Setter
    private int totalZonas;

    @Getter @Setter
    private int capacidade;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.ALL)
    @Getter @Setter
    @JsonManagedReference
    private List<Moto> motos = new ArrayList<>();


    public void calculaCapacidade() {
        double areaTotal = comprimento * largura;

        int zonas = (int) (areaTotal / 25);
        int capacidade = (int) (areaTotal / 2);
        if (zonas < 1){
            zonas = 1;
        }
        this.totalZonas = zonas;
        this.capacidade = capacidade;
    }

    @PrePersist
    @PreUpdate
    public void atualizarCapacidade(){
        calculaCapacidade();
    }



    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Patio patio = (Patio) obj;
        return Objects.equals(id, patio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



}
