package br.com.cabreira.minhastarefas.controller.assembler;

import br.com.cabreira.minhastarefas.controller.TarefaCategoriaController;
import br.com.cabreira.minhastarefas.controller.response.TarefaCategoriaResponse;
import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TarefaCategoriaModelAssembler implements RepresentationModelAssembler<TarefaCategoria, EntityModel<TarefaCategoriaResponse>> {

        @Autowired
        private ModelMapper mapper;


        @Override
        public EntityModel<TarefaCategoriaResponse> toModel (TarefaCategoria tarefaCategoria){
            TarefaCategoriaResponse tarefaCategoriaResponse = mapper.map(tarefaCategoria, TarefaCategoriaResponse.class);
            EntityModel<TarefaCategoriaResponse> tarefaCategoriaModel = EntityModel.of(tarefaCategoriaResponse, linkTo(methodOn(TarefaCategoriaController.class).categoria(tarefaCategoriaResponse.getId())).withSelfRel()
                                                                                                              , linkTo(methodOn(TarefaCategoriaController.class).todasCategorias(new HashMap<>())).withRel("categorias")
                                                                                      );

        return tarefaCategoriaModel;
    }


}
