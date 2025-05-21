package br.com.pulse.infraestruture;

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

        // -------- INSERÇÃO 1 --------
        Beacon beacon1 = new Beacon();
        beacon1.setCodigo(UUID.randomUUID());
        beacon1.setStatus(StatusBeacon.ATIVO);

        Endereco endereco1 = new Endereco();
        endereco1.setRua("Rua das Flores");
        endereco1.setNumero("101");
        endereco1.setBairro("Centro");
        endereco1.setCidade("São Paulo");
        endereco1.setEstado("SP");
        endereco1.setCep("01010-000");

        Patio patio1 = new Patio();
        patio1.setComprimento(50.0);
        patio1.setLargura(30.0);
        patio1.setEndereco(endereco1);
        patio1.atualizarCapacidade();

        patioRepository.save(patio1);

        Moto moto1 = new Moto();
        moto1.setModelo(ModeloMoto.MOTTU_E);
        moto1.setCondicaoMecanica(CondicaoMecanica.BOM_ESTADO);
        moto1.setNumeroChassi("CHASSI001");
        moto1.setStatus(StatusMoto.PLACA_REGISTRADA);
        moto1.setPlaca("ABC-1001");
        moto1.setPatio(patio1);

        motoRepository.save(moto1);
        beacon1.setMoto(moto1);
        beaconRepository.save(beacon1);

        // -------- INSERÇÃO 2 --------
        Beacon beacon2 = new Beacon();
        beacon2.setCodigo(UUID.randomUUID());
        beacon2.setStatus(StatusBeacon.EM_MANUTENCAO);

        Endereco endereco2 = new Endereco();
        endereco2.setRua("Av. Paulista");
        endereco2.setNumero("1500");
        endereco2.setBairro("Bela Vista");
        endereco2.setCidade("São Paulo");
        endereco2.setEstado("SP");
        endereco2.setCep("01310-200");

        Patio patio2 = new Patio();
        patio2.setComprimento(60.0);
        patio2.setLargura(35.0);
        patio2.setEndereco(endereco2);
        patio2.atualizarCapacidade();

        patioRepository.save(patio2);

        Moto moto2 = new Moto();
        moto2.setModelo(ModeloMoto.MOTTU_POP_110i);
        moto2.setCondicaoMecanica(CondicaoMecanica.NECESSITA_REVISAO);
        moto2.setNumeroChassi("CHASSI002");
        moto2.setStatus(StatusMoto.INVESTIGACAO_POR_ACIDENTE);
        moto2.setPlaca("DEF-2002");
        moto2.setPatio(patio2);

        motoRepository.save(moto2);
        beacon2.setMoto(moto2);
        beaconRepository.save(beacon2);

        // -------- INSERÇÃO 3 --------
        Beacon beacon3 = new Beacon();
        beacon3.setCodigo(UUID.randomUUID());
        beacon3.setStatus(StatusBeacon.INATIVO);

        Endereco endereco3 = new Endereco();
        endereco3.setRua("Rua do Comércio");
        endereco3.setNumero("300");
        endereco3.setBairro("Jardim");
        endereco3.setCidade("Campinas");
        endereco3.setEstado("SP");
        endereco3.setCep("13050-000");

        Patio patio3 = new Patio();
        patio3.setComprimento(45.0);
        patio3.setLargura(25.0);
        patio3.setEndereco(endereco3);
        patio3.atualizarCapacidade();

        patioRepository.save(patio3);

        Moto moto3 = new Moto();
        moto3.setModelo(ModeloMoto.SPORT_110i);
        moto3.setCondicaoMecanica(CondicaoMecanica.PEQUENOS_REPAROS);
        moto3.setNumeroChassi("CHASSI003");
        moto3.setStatus(StatusMoto.SEM_PLACA);
        moto3.setPlaca("GHI-3003");
        moto3.setPatio(patio3);

        motoRepository.save(moto3);
        beacon3.setMoto(moto3);
        beaconRepository.save(beacon3);

        // -------- INSERÇÃO 4 --------
        Beacon beacon4 = new Beacon();
        beacon4.setCodigo(UUID.randomUUID());
        beacon4.setStatus(StatusBeacon.ATIVO);

        Endereco endereco4 = new Endereco();
        endereco4.setRua("Rua das Acácias");
        endereco4.setNumero("404");
        endereco4.setBairro("Parque Verde");
        endereco4.setCidade("Guarulhos");
        endereco4.setEstado("SP");
        endereco4.setCep("07020-300");

        Patio patio4 = new Patio();
        patio4.setComprimento(70.0);
        patio4.setLargura(40.0);
        patio4.setEndereco(endereco4);
        patio4.atualizarCapacidade();

        patioRepository.save(patio4);

        Moto moto4 = new Moto();
        moto4.setModelo(ModeloMoto.MOTTU_E);
        moto4.setCondicaoMecanica(CondicaoMecanica.GRAVEMENTE_DANIFICADA);
        moto4.setNumeroChassi("CHASSI004");
        moto4.setStatus(StatusMoto.INVESTIGACAO_POR_FURTO);
        moto4.setPlaca("JKL-4004");
        moto4.setPatio(patio4);

        motoRepository.save(moto4);
        beacon4.setMoto(moto4);
        beaconRepository.save(beacon4);

        // -------- INSERÇÃO 5 --------
        Beacon beacon5 = new Beacon();
        beacon5.setCodigo(UUID.randomUUID());
        beacon5.setStatus(StatusBeacon.EM_MANUTENCAO);

        Endereco endereco5 = new Endereco();
        endereco5.setRua("Alameda Santos");
        endereco5.setNumero("505");
        endereco5.setBairro("Consolação");
        endereco5.setCidade("São Paulo");
        endereco5.setEstado("SP");
        endereco5.setCep("01419-000");

        Patio patio5 = new Patio();
        patio5.setComprimento(55.0);
        patio5.setLargura(28.0);
        patio5.setEndereco(endereco5);
        patio5.atualizarCapacidade();

        patioRepository.save(patio5);

        Moto moto5 = new Moto();
        moto5.setModelo(ModeloMoto.MOTTU_POP_110i);
        moto5.setCondicaoMecanica(CondicaoMecanica.INOPERANTE);
        moto5.setNumeroChassi("CHASSI005");
        moto5.setStatus(StatusMoto.PLACA_REGISTRADA);
        moto5.setPlaca("MNO-5005");
        moto5.setPatio(patio5);

        motoRepository.save(moto5);
        beacon5.setMoto(moto5);
        beaconRepository.save(beacon5);
    }
}
