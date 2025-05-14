package br.com.pulse.runners;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.domainmodel.Endereco;
import br.com.pulse.domainmodel.Moto;
import br.com.pulse.domainmodel.Patio;
import br.com.pulse.domainmodel.enuns.CondicaoMecanica;
import br.com.pulse.domainmodel.enuns.ModeloMoto;
import br.com.pulse.domainmodel.enuns.StatusBeacon;
import br.com.pulse.domainmodel.enuns.StatusMoto;
import br.com.pulse.repositories.BeaconRepository;
import br.com.pulse.repositories.MotoRepository;
import br.com.pulse.repositories.PatioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInicial implements CommandLineRunner {

    private final MotoRepository motoRepository;
    private final BeaconRepository beaconRepository;
    private final PatioRepository patioRepository;

    @Override
    public void run(String... args) throws Exception {

        UUID codigo = UUID.randomUUID();
        Beacon beacon = new Beacon();
        beacon.setCodigo(codigo);
        beacon.setStatus(StatusBeacon.ATIVO);


        Endereco endereco = new Endereco();
        endereco.setRua("Rua do Pátio");
        endereco.setNumero("223");
        endereco.setBairro("Higienópolis");
        endereco.setCidade("São Paulo");
        endereco.setEstado("São Paulo");
        endereco.setCep("78787878");



        Patio patio = new Patio();
        patio.setComprimento(50.0);
        patio.setLargura(30.0);
        patio.atualizarCapacidade();
        patio.setEndereco(endereco);


        patioRepository.save(patio);


        Moto moto = new Moto();
        moto.setModelo(ModeloMoto.MOTTU_E);
        moto.setCondicaoMecanica(CondicaoMecanica.GRAVEMENTE_DANIFICADA);
        moto.setNumeroChassi("7878787878787");
        moto.setStatus(StatusMoto.PLACA_REGISTRADA);
        moto.setPlaca("ABC-1234");
        moto.setPatio(patio);


        motoRepository.save(moto);


        beacon.setMoto(moto);


        beaconRepository.save(beacon);
    }
}
