package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.controller.assembler.TarefaModelAssembler;
import br.com.cabreira.minhastarefas.controller.request.TarefaRequest;
import br.com.cabreira.minhastarefas.controller.response.TarefaResponse;
import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private ModelMapper mapper;

   @Autowired
    private TarefaService service;

   @Autowired
   private TarefaModelAssembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<TarefaResponse>> todasTarefas(@RequestParam Map<String,String> parametros){
        List<Tarefa> tarefas = new ArrayList<>();

        if(parametros.isEmpty()) {
            tarefas = service.getTodasTarefas();
        }else {
            String descricao = parametros.get("descricao");
            tarefas = service.getTarefasPorDescricao(descricao);
        }
        final List<EntityModel<TarefaResponse>> tarefasModel = tarefas.stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(tarefasModel, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<TarefaResponse>  tarefa(@PathVariable Integer id){
        final Tarefa tarefa = service.getTarefaPorId(id);
        EntityModel<TarefaResponse> tarefaModel = assembler.toModel(tarefa);
        return tarefaModel;
    }
            /*produces = JSON*/
    @PostMapping()
    public ResponseEntity<EntityModel<TarefaResponse>> salvarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest){
        Tarefa tarefa = service.salvarTarefa(mapper.map(tarefaRequest, Tarefa.class));

        EntityModel<TarefaResponse> tarefaModel = assembler.toModel(tarefa);
        return ResponseEntity.created(tarefaModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tarefaModel);

    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Integer id){
        service.deletePorId(id);
    }


    @PutMapping("/{id}/iniciar")
    public EntityModel<TarefaResponse> iniciarTarefa(@PathVariable Integer id){
        Tarefa tarefa = service.iniciarTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PutMapping("/{id}/concluir")
    public EntityModel<TarefaResponse> concluirTarefa(@PathVariable Integer id){
        Tarefa tarefa = service.concluirTarefaPorId(id);
        return assembler.toModel(tarefa);
    }

    @PutMapping("/{id}/cancelar")
    public EntityModel<TarefaResponse> cancelarTarefa(@PathVariable Integer id){
        Tarefa tarefa = service.cancelarTarefaPorId(id);
        return assembler.toModel(tarefa);
    }


}
