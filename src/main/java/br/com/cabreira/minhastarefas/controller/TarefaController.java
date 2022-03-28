package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class TarefaController {

    @Autowired
    private TarefaRepository repositorio;

    @GetMapping("/tarefa")
    public List<Tarefa> todasTarefas(@RequestParam Map<String,String> parametros){
        if(parametros.isEmpty())
            return repositorio.findAll();

        String descricao = parametros.get("descricao");
        return repositorio.findByDescricaoLike("%"+descricao+"%");
    }

    @GetMapping("/tarefa/{id}")
    public Tarefa tarefa(@PathVariable Integer id){
        return repositorio.findById(id).orElse(null);
    }
            /*produces = JSON*/
    @PostMapping(value = "/tarefa")
    public Tarefa salvarTarefa(@Valid @RequestBody Tarefa tarefa){
        return repositorio.save(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void excluirTarefa(@PathVariable Integer id){
        repositorio.deleteById(id);
    }

}
