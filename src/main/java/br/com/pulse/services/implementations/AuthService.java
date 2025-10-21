package br.com.pulse.services.implementations;

import br.com.pulse.domainmodel.entities.Employee;
import br.com.pulse.exceptions.InvalidPasswordException;
import br.com.pulse.infraestruture.security.JwtUtil;
import br.com.pulse.repositories.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String cpf, String password) {
        Employee employee = employeeRepository.findByCpf(cpf)
                .orElseThrow(() -> new InvalidPasswordException("Usuário não encontrado"));

        if (!passwordEncoder.matches(password, employee.getPassword())) {
            throw new InvalidPasswordException("Senha incorreta");
        }

        return jwtUtil.generateToken(cpf);
    }
}
