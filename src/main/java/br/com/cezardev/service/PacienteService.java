package br.com.cezardev.service;

import br.com.cezardev.dto.PacienteDTO;
import br.com.cezardev.entity.PacienteEntity;
import br.com.cezardev.repository.PacienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // ============================
    // LISTAR TODOS
    // ============================
    public List<PacienteDTO> listarTodos() {
        return pacienteRepository.findAll()
                .stream()
                .map(PacienteDTO::new)
                .toList();
    }

    // ============================
    // INSERIR
    // ============================
    public PacienteDTO inserir(PacienteDTO dto) {

        String cpfLimpo = limparCpf(dto.getCpf());

        if (pacienteRepository.findByCpf(cpfLimpo).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um cadastro com o CPF informado."
            );
        }

        dto.setCpf(cpfLimpo);

        PacienteEntity entity = new PacienteEntity(dto);
        pacienteRepository.save(entity);

        return new PacienteDTO(entity);
    }

    // ============================
    // ATUALIZAR
    // ============================
    public PacienteDTO atualizar(Long id, PacienteDTO dto) {

        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Paciente não encontrado"
                        )
                );

        String cpfLimpo = limparCpf(dto.getCpf());

        pacienteRepository.findByCpf(cpfLimpo)
                .ifPresent(p -> {
                    if (!p.getId().equals(id)) {
                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Já existe um cadastro com o CPF informado."
                        );
                    }
                });

        paciente.setNome(dto.getNome());
        paciente.setCpf(cpfLimpo);
        paciente.setDataNascimento(dto.getDataNascimento());

        pacienteRepository.save(paciente);

        return new PacienteDTO(paciente);
    }

    // ============================
    // EXCLUIR
    // ============================
    public void excluir(Long id) {

        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Paciente não encontrado"
                        )
                );

        pacienteRepository.delete(paciente);
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    public PacienteDTO buscarPorId(Long id) {

        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Paciente não encontrado"
                        )
                );

        return new PacienteDTO(paciente);
    }

    // ============================
    // MÉTODO AUXILIAR
    // ============================
    private String limparCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }
}