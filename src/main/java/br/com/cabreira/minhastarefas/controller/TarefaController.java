package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.controller.response.TarefaResponse;
import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TarefaController {

    @Autowired
    private ModelMapper mapper;

   @Autowired
    private TarefaService service;

    @GetMapping("/tarefa")
    public List<TarefaResponse> todasTarefas(@RequestParam Map<String,String> parametros){
        List<Tarefa> tarefas = new ArrayList<>();

        if(parametros.isEmpty()) {
            tarefas = service.getTodasTarefas();
        }else {
            String descricao = parametros.get("descricao");
            tarefas = service.getTarefasPorDescricao(descricao);
        }
        final List<TarefaResponse> tarefasResponse = tarefas.stream().map(tarefa -> mapper.map(tarefa, TarefaResponse.class)).collect(Collectors.toList());
        return tarefasResponse;
    }

    @GetMapping("/tarefa/{id}")
    public TarefaResponse tarefa(@PathVariable Integer id){
        final Tarefa tarefa = service.getTarefaPorId(id);
        return mapper.map(tarefa, TarefaResponse.class);
    }
            /*produces = JSON*/
    @PostMapping(value = "/tarefa")
    public TarefaResponse salvarTarefa(@Valid @RequestBody Tarefa tarefa){
        return mapper.map(service.salvarTarefa(tarefa),TarefaResponse.class);
    }

    @DeleteMapping("/tarefa/{id}")
    public void excluirTarefa(@PathVariable Integer id){
        service.deletePorId(id);
    }

}
