package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.repository.TarefaRepository;
import br.com.cabreira.minhastarefas.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class TarefaController {

   @Autowired
    private TarefaService service;

    @GetMapping("/tarefa")
    public List<Tarefa> todasTarefas(@RequestParam Map<String,String> parametros){
        if(parametros.isEmpty())
            return service.getTodasTarefas();

        String descricao = parametros.get("descricao");
        return service.getTarefasPorDescricao(descricao);
    }

    @GetMapping("/tarefa/{id}")
    public Tarefa tarefa(@PathVariable Integer id){
        return service.getTarefaPorId(id);
    }
            /*produces = JSON*/
    @PostMapping(value = "/tarefa")
    public Tarefa salvarTarefa(@Valid @RequestBody Tarefa tarefa){
        return service.salvarTarefa(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void excluirTarefa(@PathVariable Integer id){
        service.deletePorId(id);
    }

}
