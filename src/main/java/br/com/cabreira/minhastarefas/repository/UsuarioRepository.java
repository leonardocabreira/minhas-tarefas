package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
