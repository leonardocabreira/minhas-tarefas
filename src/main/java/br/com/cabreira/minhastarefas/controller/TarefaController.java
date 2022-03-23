package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TarefaController {

    @Autowired
    private TarefaRepository repositorio;

    @GetMapping("/tarefa")
    public List<Tarefa> todasTarefas(){
        return repositorio.findAll();
    }

    @GetMapping("/tarefa/{id}")
    public Tarefa tarefa(@PathVariable Integer id){
        return repositorio.findById(id).orElse(null);
    }

    @PostMapping("/tarefa")
    public Tarefa salvarTarefa(@RequestBody Tarefa tarefa){
        return repositorio.save(tarefa);
    }

    @DeleteMapping("/tarefa/{id}")
    public void excluirTarefa(@PathVariable Integer id){
        repositorio.deleteById(id);
    }
}
