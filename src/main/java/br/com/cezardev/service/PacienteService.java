package br.com.cezardev.service;

import br.com.cezardev.dto.PacienteDTO;
import br.com.cezardev.entity.PacienteEntity;
import br.com.cezardev.repository.PacienteRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // ============================
    // BUSCAR COM FILTRO
    // ============================
    public Page<PacienteDTO> buscar(String nome, String cpf, Pageable pageable) {

        Page<PacienteEntity> pacientes;

        // filtro por nome
        if (nome != null && !nome.trim().isEmpty()) {

            pacientes = pacienteRepository
                    .findByNomeContainingIgnoreCase(
                            nome.trim(),
                            pageable
                    );

        }

        // filtro por cpf
        else if (cpf != null && !cpf.trim().isEmpty()) {

            String cpfLimpo = limparCpf(cpf);

            pacientes = pacienteRepository
                    .findByCpfContaining(
                            cpfLimpo,
                            pageable
                    );

        }

        // sem filtro
        else {

            pacientes = pacienteRepository.findAll(pageable);

        }

        return pacientes.map(PacienteDTO::new);
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

        PacienteEntity paciente = pacienteRepository
                .findById(id)
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

        PacienteEntity paciente = pacienteRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Paciente não encontrado"
                        )
                );

        pacienteRepository.delete(paciente);
    }

    // ============================
    // LIMPAR CPF
    // ============================
    private String limparCpf(String cpf) {

        if (cpf == null) return null;

        return cpf.replaceAll("\\D", "");
    }
}