package br.com.cezardev.service;

import br.com.cezardev.dto.PacienteDTO;
import br.com.cezardev.entity.PacienteEntity;
import br.com.cezardev.repository.PacienteRepository;
import org.springframework.stereotype.Service;

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
        List<PacienteEntity> pacientes = pacienteRepository.findAll();
        return pacientes.stream()
                .map(PacienteDTO::new)
                .toList();
    }

    // ============================
    // INSERIR
    // ============================
    public PacienteDTO inserir(PacienteDTO dto) {
        PacienteEntity entity = new PacienteEntity(dto);
        pacienteRepository.save(entity);
        return new PacienteDTO(entity);
    }

    // ============================
    // ATUALIZAR
    // ============================
    public PacienteDTO atualizar(Long id, PacienteDTO dto) {

        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setDataNascimento(dto.getDataNascimento());

        pacienteRepository.save(paciente);

        return new PacienteDTO(paciente);
    }

    // ============================
    // EXCLUIR
    // ============================
    public void excluir(Long id) {

        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        pacienteRepository.delete(paciente);
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    public PacienteDTO buscarPorId(Long id) {

        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        return new PacienteDTO(paciente);
    }
}