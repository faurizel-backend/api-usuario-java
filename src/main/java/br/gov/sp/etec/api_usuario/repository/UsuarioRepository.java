package br.gov.sp.etec.api_usuario.repository;

import br.gov.sp.etec.api_usuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {
    UsuarioEntity findByEmail(String email);

}
