package br.com.cezardev.service;

import br.com.cezardev.dto.MedicoDTO;
import br.com.cezardev.entity.MedicoEntity;
import br.com.cezardev.repository.MedicoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    // ==========================
    // LISTAR COM FILTRO
    // ==========================
    public Page<MedicoDTO> listar(String nome, String crm, int page, int size) {

        PageRequest pageable = PageRequest.of(page, size);

        Page<MedicoEntity> medicos;

        if (nome != null && !nome.isBlank()) {

            medicos = medicoRepository
                    .findByNomeContainingIgnoreCase(nome.trim(), pageable);

        } else if (crm != null && !crm.isBlank()) {

            medicos = medicoRepository
                    .findByCrmContaining(crm.trim(), pageable);

        } else {

            medicos = medicoRepository.findAll(pageable);

        }

        return medicos.map(MedicoDTO::new);
    }

    // ==========================
    // INSERIR
    // ==========================
    public MedicoDTO inserir(MedicoDTO dto) {

        if (medicoRepository.findByCrm(dto.getCrm()).isPresent()) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um médico cadastrado com este CRM."
            );
        }

        MedicoEntity entity = new MedicoEntity(dto);

        medicoRepository.save(entity);

        return new MedicoDTO(entity);
    }

    // ==========================
    // ATUALIZAR
    // ==========================
    public MedicoDTO atualizar(Long id, MedicoDTO dto) {

        MedicoEntity medico = medicoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Médico não encontrado"
                        )
                );

        medicoRepository.findByCrm(dto.getCrm())
                .ifPresent(m -> {

                    if (!m.getId().equals(id)) {

                        throw new ResponseStatusException(
                                HttpStatus.CONFLICT,
                                "Já existe um médico com este CRM."
                        );

                    }

                });

        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());

        medicoRepository.save(medico);

        return new MedicoDTO(medico);
    }

    // ==========================
    // EXCLUIR
    // ==========================
    public void excluir(Long id) {

        MedicoEntity medico = medicoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Médico não encontrado"
                        )
                );

        medicoRepository.delete(medico);
    }
}