package br.com.cabreira.minhastarefas.services;

import br.com.cabreira.minhastarefas.exception.TarefaStatusExeption;
import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.model.TarefaStatus;
import br.com.cabreira.minhastarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repositorio;

    public List<Tarefa> getTodasTarefas() {
        return repositorio.findAll();
    }

    public List<Tarefa> getTarefasPorDescricao(String descricao){
        return repositorio.findByDescricaoLike("%"+descricao+"%");
    }

    public Tarefa getTarefaPorId(Integer id){
        return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Tarefa salvarTarefa(Tarefa tarefa){
        return repositorio.save(tarefa);
    }

    public void deletePorId(Integer id){
        repositorio.deleteById(id);
    }

    public Tarefa iniciarTarefaPorId(Integer id){
        final Tarefa tarefa = getTarefaPorId(id);

        if(!TarefaStatus.ABERTO.equals(tarefa.getStatus()))
            throw new TarefaStatusExeption();

        tarefa.setStatus(TarefaStatus.EM_ANDAMENTO);
        salvarTarefa(tarefa);
        return tarefa;
    }

    public Tarefa concluirTarefaPorId(Integer id){
        final Tarefa tarefa = getTarefaPorId(id);

        if(TarefaStatus.CANCELADA.equals(tarefa.getStatus()))
            throw new TarefaStatusExeption();

        tarefa.setStatus(TarefaStatus.CONCLUIDA);
        salvarTarefa(tarefa);
        return tarefa;
    }

    public Tarefa cancelarTarefaPorId(Integer id){
        final Tarefa tarefa = getTarefaPorId(id);

        if(TarefaStatus.CONCLUIDA.equals(tarefa.getStatus()))
            throw new TarefaStatusExeption();

        tarefa.setStatus(TarefaStatus.CANCELADA);
        salvarTarefa(tarefa);
        return tarefa;
    }



}
