package br.com.cabreira.minhastarefas.services;

import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

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
}
