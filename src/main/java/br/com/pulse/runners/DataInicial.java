package br.com.pulse.runners;

import br.com.pulse.domainmodel.Beacon;
import br.com.pulse.domainmodel.Moto;
import br.com.pulse.repositories.MotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInicial implements CommandLineRunner {

    private final MotoRepository motoRepository;

    @Override
    public void run(String... args) {
        UUID codigo = UUID.randomUUID();


        Beacon beacon = new Beacon();
        beacon.setCodigoUnico(codigo);

        Moto moto = new Moto();
        moto.setModelo("Mottu E");
        moto.setCor("Verde");
        moto.setCondicaoMecanica("Danificado");
        moto.setNumeroChassi("7878787878787");
        moto.setStatus("Sem placa");
        moto.setPlaca(null);
        moto.setPatio(null);
        moto.setCodigoBeacon(beacon);
        beacon.setMoto(moto);

        motoRepository.save(moto);
    }
}
