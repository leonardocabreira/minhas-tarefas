package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {

    List<Tarefa> findByDescricaoLike(String descricao);
}
