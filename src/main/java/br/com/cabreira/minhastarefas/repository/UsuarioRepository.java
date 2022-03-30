package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    List<Usuario> findByNomeLike(String nome);
}
