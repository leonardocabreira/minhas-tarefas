package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.controller.assembler.TarefaCategoriaModelAssembler;
import br.com.cabreira.minhastarefas.controller.request.TarefaCategoriaRequest;
import br.com.cabreira.minhastarefas.controller.response.TarefaCategoriaResponse;
import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import br.com.cabreira.minhastarefas.services.TarefaCategoriaService;
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
public class TarefaCategoriaController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TarefaCategoriaService service;

    @Autowired
    private TarefaCategoriaModelAssembler assembler;

    @GetMapping("/categoria")
    public CollectionModel<EntityModel<TarefaCategoriaResponse>> todasCategorias(@RequestParam Map<String,String> parametros){
        List<TarefaCategoria> categorias = new ArrayList<>();
        if(parametros.isEmpty()){
            categorias = service.getTodasCategorias();
        }else{
            categorias = service.getCategoriaPorNome(parametros.get("nome"));
        }

        List<EntityModel<TarefaCategoriaResponse>> tarefasCategoriasModel = categorias.stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(tarefasCategoriasModel, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TarefaCategoriaController.class).todasCategorias(new HashMap<>())).withSelfRel());
    }

    @GetMapping("/categoria/{id}")
    public EntityModel<TarefaCategoriaResponse> categoria(@PathVariable Integer id){
        TarefaCategoria tarefaCategoria = service.getCategoria(id);
        return assembler.toModel(tarefaCategoria);
    }

    @PostMapping("/categoria")
    public ResponseEntity<EntityModel<TarefaCategoriaResponse>> salvarCategoria(@Valid @RequestBody TarefaCategoriaRequest tarefaCategoriaRequest) {
        TarefaCategoria tarefaCategoria = service.salvarCategoria(mapper.map(tarefaCategoriaRequest, TarefaCategoria.class));
        EntityModel<TarefaCategoriaResponse> tarefaCategoriaModel = assembler.toModel(tarefaCategoria);
        return ResponseEntity.created(tarefaCategoriaModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tarefaCategoriaModel);
    }

    @DeleteMapping("/categoria/{id}")
    public void excluirCategoria(@PathVariable Integer id){
        service.deleteCategoriaPorId(id);
    }

}
