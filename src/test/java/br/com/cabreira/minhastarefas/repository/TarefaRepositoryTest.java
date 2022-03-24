package br.com.cabreira.minhastarefas.repository;

import br.com.cabreira.minhastarefas.model.Tarefa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TarefaRepositoryTest {

    @Autowired
    private TarefaRepository repositorio;

    @Test
    void testFindByNomeCategoria(){
        final List<Tarefa> tarefas = repositorio.findByNomeCategoria("Estudos");
        Assertions.assertEquals(1, tarefas.size(),"A quantidade de tarefas retornada está incorreta");

    }

    @Test
    void testFindByNomeCategoriaComNomeIncorreto(){
        final List<Tarefa> tarefas = repositorio.findByNomeCategoria("TESTE");
        Assertions.assertEquals(0, tarefas.size(),"A quantidade de tarefas retornada está incorreta");

    }

    @Test
    void testTarefasPorUsuario(){
        final List<Tarefa> tarefas = repositorio.tarefasPorUsuario("Leonardo");
        Assertions.assertEquals(1, tarefas.size(),"A quantidade de tarefas retornada está incorreta");
    }

    @Test
    void testTarefasPorUsuarioComNomeIncorreto(){
        final List<Tarefa> tarefas = repositorio.tarefasPorUsuario("TESTE");
        Assertions.assertEquals(0, tarefas.size(),"A quantidade de tarefas retornada está incorreta");
    }

}
