package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa,Integer> {

    List<Tarefa> findByDescricaoLike(String descricao);

    @Query("select t from Tarefa t inner join t.categoria c where c.nome = ?1")
    List<Tarefa>findByNomeCategoria(String nomeCategoria);

    List<Tarefa>tarefasPorUsuario(String nomeUsuario);
}
