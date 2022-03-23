package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaCategoriaRepository extends JpaRepository<TarefaCategoria,Integer> {
}
