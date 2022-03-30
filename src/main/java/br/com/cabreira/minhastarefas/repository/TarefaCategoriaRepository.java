package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria,Integer> {

    List<TarefaCategoria> findByNomeLike(String nome);
}
