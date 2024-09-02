package br.com.cezardev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cezardev.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long>{

}