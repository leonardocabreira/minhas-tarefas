package br.com.cabreira.minhastarefas.services;

import br.com.cabreira.minhastarefas.exception.TarefaStatusExeption;
import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.model.TarefaStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TarefaServiceIntegrationTest {

    @Autowired
    private TarefaService service;

    @Test
    void deveIniciarTarefa(){
        final int id = 3;
        final Tarefa tarefa = service.iniciarTarefaPorId(id);
        Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO,tarefa.getStatus());
    }

    @Test
    void naoDeveIniciarTarefaQueNaoEstejaEmAberto(){
        final int id = 4;
        final Tarefa tarefa  = service.getTarefaPorId(id);
        tarefa.setStatus(TarefaStatus.CONCLUIDA);
        service.salvarTarefa(tarefa);
        Assertions.assertThrows(TarefaStatusExeption.class,
                () -> service.iniciarTarefaPorId(id));

    }

    @Test
    void naoDeveConcluirTarefaCancelada(){
        final int id = 4;
        final Tarefa tarefa  = service.getTarefaPorId(id);
        tarefa.setStatus(TarefaStatus.CANCELADA);
        service.salvarTarefa(tarefa);
        Assertions.assertThrows(TarefaStatusExeption.class,
                () -> service.concluirTarefaPorId(id));

    }

    @Test
    void naoDeveCancelarTarefaConcluida(){
        final int id = 4;
        final Tarefa tarefa  = service.getTarefaPorId(id);
        tarefa.setStatus(TarefaStatus.CONCLUIDA);
        service.salvarTarefa(tarefa);
        Assertions.assertThrows(TarefaStatusExeption.class,
                () -> service.cancelarTarefaPorId(id));

    }
}
