package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.controller.request.TarefaRequest;
import br.com.cabreira.minhastarefas.controller.response.TarefaResponse;
import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.services.TarefaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private ModelMapper mapper;

   @Autowired
    private TarefaService service;

    @GetMapping
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

    @GetMapping("/{id}")
    public EntityModel<TarefaResponse>  tarefa(@PathVariable Integer id){
        final Tarefa tarefa = service.getTarefaPorId(id);
        TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);
        EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse, linkTo(methodOn(TarefaController.class).tarefa(id)).withSelfRel()
                                                                               , linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withRel("tarefas")
                                                                               , linkTo(methodOn(TarefaCategoriaController.class).categoria(tarefaResponse.getCategoriaId())).withRel("categoria")
                                                                               , linkTo(methodOn(UsuarioController.class).usuario(tarefaResponse.getUsuarioId())).withRel("usuario"));
        return tarefaModel;
    }
            /*produces = JSON*/
    @PostMapping()
    public EntityModel<TarefaResponse> salvarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest){
        Tarefa tarefa = mapper.map(tarefaRequest, Tarefa.class);
        TarefaResponse tarefaResponse = mapper.map(service.salvarTarefa(tarefa), TarefaResponse.class);
        EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse, linkTo(methodOn(TarefaController.class).tarefa(tarefaResponse.getId())).withSelfRel()
                , linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withRel("tarefas")
                , linkTo(methodOn(TarefaCategoriaController.class).categoria(tarefaResponse.getCategoriaId())).withRel("categoria")
                , linkTo(methodOn(UsuarioController.class).usuario(tarefaResponse.getUsuarioId())).withRel("usuario"));
        return tarefaModel;

    }

    @DeleteMapping("/{id}")
    public void excluirTarefa(@PathVariable Integer id){
        service.deletePorId(id);
    }

}
