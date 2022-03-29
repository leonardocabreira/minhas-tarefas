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
        final Tarefa tarefa = service.iniciarTarefaPorId(3);
        Assertions.assertEquals(TarefaStatus.EM_ANDAMENTO,tarefa.getStatus());
    }

    @Test
    void naoDeveIniciarTarefaConcluida(){
        final Tarefa tarefa  = service.getTarefaPorId(3);
        tarefa.setStatus(TarefaStatus.CONCLUIDA);
        service.salvarTarefa(tarefa);
        Assertions.assertThrows(TarefaStatusExeption.class,
                () -> service.iniciarTarefaPorId(3));

    }
}
