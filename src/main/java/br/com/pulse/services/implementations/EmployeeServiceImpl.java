package br.com.pulse.services.implementations;

import br.com.pulse.domainmodel.entities.Employee;
import br.com.pulse.exceptions.InvalidPasswordException;
import br.com.pulse.exceptions.ResourceNotFoundException;
import br.com.pulse.repositories.EmployeeRepository;
import br.com.pulse.repositories.ParkingRepository;
import br.com.pulse.services.interfaces.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ParkingRepository parkingRepository;

    public EmployeeServiceImpl(EmployeeRepository repository, PasswordEncoder passwordEncoder, ParkingRepository parkingRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.parkingRepository = parkingRepository;
    }

    @Override
    public Employee save(Employee user) {
        user.validate();

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new InvalidPasswordException("Senha inválida, não pode ser nula ou vazia.");
        }

        if (!parkingRepository.existsById(user.getParkingId())) {
            throw new ResourceNotFoundException("Pátio não encontrado.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public Employee findByEmail(String email) {
        Employee user = repository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário com email " + email + " não encontrado.");
        }
        return user;
    }

    @Override
    public Employee findByCpf(String cpf) {
        Employee user = repository.findByCpf(cpf).orElseThrow(()-> new ResourceNotFoundException("Usuário com CPF " + cpf + " não encontrado."));
        return user;
    }

    @Override
    public List<Employee> findByParkingId(Long parkingId) {
        List<Employee> users = repository.findAllByParkingId(parkingId);
        if (users == null || users.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum usuário encontrado para o pátio");
        }
        return users;
    }

    @Override
    public void deleteUserByCpf(String cpf) {
        Employee user = repository.findByCpf(cpf).orElseThrow(()-> new ResourceNotFoundException("Usuário com CPF " + cpf + " não encontrado."));
        repository.delete(user);
    }

    @Override
    public void updateUser(Employee employee) {
        Employee existing = repository.findByCpf(employee.getCpf()).orElseThrow(()-> new ResourceNotFoundException("Usuário com CPF não encontrado.")); // ou findById(employee.getId())
        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setRole(employee.getRole());
        existing.setParkingId(employee.getParkingId());
        existing.setBirthDate(employee.getBirthDate());



        repository.save(existing);
    }

    @Override
    public String getLoggedUserName(String email) {
        Employee user = repository.findByEmail(email);
        if (user == null) {
            throw new ResourceNotFoundException("Usuário com email " + email + " não encontrado.");
        }
        return user.getName();
    }




}
