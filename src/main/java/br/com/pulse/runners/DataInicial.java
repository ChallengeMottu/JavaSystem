//package br.com.pulse.runners;
//
//import br.com.pulse.domainmodel.Beacon;
//import br.com.pulse.domainmodel.Moto;
//import br.com.pulse.domainmodel.Patio;
//import br.com.pulse.domainmodel.enuns.CondicaoMecanica;
//import br.com.pulse.domainmodel.enuns.ModeloMoto;
//import br.com.pulse.domainmodel.enuns.StatusBeacon;
//import br.com.pulse.repositories.MotoRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.UUID;
//
//@Component
//@RequiredArgsConstructor
//public class DataInicial implements CommandLineRunner {
//
//    private final MotoRepository motoRepository;
//
//    @Override
//    public void run(String... args) {
//        UUID codigo = UUID.randomUUID();
//
//
//        Beacon beacon = new Beacon();
//        Patio patio = new Patio();
//        patio.
//        beacon.setCodigo(codigo);
//
//        Moto moto = new Moto();
//        moto.setModelo(ModeloMoto.MOTTU_E);
//        moto.setCor("Verde");
//        moto.setCondicaoMecanica(CondicaoMecanica.GRAVEMENTE_DANIFICADA);
//        moto.setNumeroChassi("7878787878787");
//        moto.setStatus(StatusBeacon.ATIVO);
//        moto.setPlaca(null);
//        moto.setPatio(null);
//        moto.setBeacon(beacon);
//        beacon.setMoto(moto);
//
//        motoRepository.save(moto);
//    }
//}
