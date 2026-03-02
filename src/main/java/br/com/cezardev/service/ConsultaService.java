package br.com.cezardev.service;

import br.com.cezardev.dto.ConsultaDTO;
import br.com.cezardev.dto.ConsultaResponseDTO;
import br.com.cezardev.entity.ConsultaEntity;
import br.com.cezardev.entity.MedicoEntity;
import br.com.cezardev.entity.PacienteEntity;
import br.com.cezardev.repository.ConsultaRepository;
import br.com.cezardev.repository.MedicoRepository;
import br.com.cezardev.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository,
                           MedicoRepository medicoRepository,
                           PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // Método existente para inserir nova consulta
    public ConsultaResponseDTO inserir(ConsultaDTO dto) {
        ConsultaEntity entity = new ConsultaEntity();

        MedicoEntity medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));
        PacienteEntity paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        // ======= VALIDAÇÃO: impedir consulta no mesmo dia e horário =======
        boolean existeConsulta = consultaRepository.existsByMedicoAndDataAndHorario(
                medico, dto.getData(), dto.getHorario()
        );
        if (existeConsulta) {
            throw new RuntimeException("Já existe uma consulta agendada para este médico nesse dia e horário.");
        }
        // ==================================================================

        entity.setMedico(medico);
        entity.setPaciente(paciente);
        entity.setData(dto.getData());
        entity.setHorario(dto.getHorario());
        entity.setStatus(dto.getStatus());

        consultaRepository.save(entity);

        ConsultaResponseDTO response = new ConsultaResponseDTO();
        response.setId(entity.getId());
        response.setMedicoNome(medico.getNome());
        response.setPacienteNome(paciente.getNome());
        response.setData(entity.getData().toString());
        response.setHorario(entity.getHorario().toString());
        response.setStatus(entity.getStatus());
        return response;
    }

    // Método existente para listar todas as consultas
    public List<ConsultaResponseDTO> listarTodos() {
        return consultaRepository.findAll().stream().map(entity -> {
            ConsultaResponseDTO dto = new ConsultaResponseDTO();
            dto.setId(entity.getId());
            dto.setMedicoNome(entity.getMedico().getNome());
            dto.setPacienteNome(entity.getPaciente().getNome());
            dto.setData(entity.getData().toString());
            dto.setHorario(entity.getHorario().toString());
            dto.setStatus(entity.getStatus());
            return dto;
        }).collect(Collectors.toList());
    }

    // NOVO MÉTODO: atualizar apenas o status de uma consulta
    public ConsultaResponseDTO atualizarStatus(Long id, String novoStatus) {
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        consulta.setStatus(novoStatus);
        consultaRepository.save(consulta);

        ConsultaResponseDTO dto = new ConsultaResponseDTO();
        dto.setId(consulta.getId());
        dto.setMedicoNome(consulta.getMedico().getNome());
        dto.setPacienteNome(consulta.getPaciente().getNome());
        dto.setData(consulta.getData().toString());
        dto.setHorario(consulta.getHorario().toString());
        dto.setStatus(consulta.getStatus());
        return dto;
    }
}