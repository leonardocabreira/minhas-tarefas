package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {
}
