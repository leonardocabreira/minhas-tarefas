package br.com.cabreira.minhastarefas.services;

import br.com.cabreira.minhastarefas.exception.TarefaStatusExeption;
import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.model.TarefaStatus;
import br.com.cabreira.minhastarefas.repository.TarefaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @Mock
    private TarefaRepository repositorio;

    @InjectMocks
    private TarefaService service;


    @Test
    void naoDeveIniciarTarefaQueNaoEstejaEmAberto(){
        final int id = 1;
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setDescricao("Teste1");
        tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);
        Mockito.when(repositorio.findById(id)).thenReturn(Optional.of(tarefa));
        Assertions.assertThrows(TarefaStatusExeption.class,
                () -> service.iniciarTarefaPorId(id));
    }

    @Test
    void naoDeveConcluirTarefaCancelada(){

        final int id = 1;
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setDescricao("Teste1");
        tarefa.setStatus(TarefaStatus.CANCELADA);
        Mockito.when(repositorio.findById(id)).thenReturn(Optional.of(tarefa));
        Assertions.assertThrows(TarefaStatusExeption.class,
                () -> service.concluirTarefaPorId(id));
    }

    @Test
    void naoDeveCancelarTarefaConcluida(){

        final int id = 1;
        Tarefa tarefa = new Tarefa();
        tarefa.setId(id);
        tarefa.setDescricao("Teste1");
        tarefa.setStatus(TarefaStatus.CONCLUIDA);
        Mockito.when(repositorio.findById(id)).thenReturn(Optional.of(tarefa));
        Assertions.assertThrows(TarefaStatusExeption.class,
                () -> service.cancelarTarefaPorId(id));
    }
}
