package br.com.cabreira.minhastarefas.controller.assembler;

import br.com.cabreira.minhastarefas.controller.TarefaCategoriaController;
import br.com.cabreira.minhastarefas.controller.TarefaController;
import br.com.cabreira.minhastarefas.controller.UsuarioController;
import br.com.cabreira.minhastarefas.controller.response.TarefaResponse;
import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.model.TarefaStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TarefaModelAssembler implements RepresentationModelAssembler<Tarefa, EntityModel<TarefaResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<TarefaResponse> toModel(Tarefa tarefa) {
        TarefaResponse tarefaResponse = mapper.map(tarefa, TarefaResponse.class);
        EntityModel<TarefaResponse> tarefaModel = EntityModel.of(tarefaResponse, linkTo(methodOn(TarefaController.class).tarefa(tarefaResponse.getId())).withSelfRel()
                                                                                , linkTo(methodOn(TarefaController.class).todasTarefas(new HashMap<>())).withRel("tarefas")
                                                                                , linkTo(methodOn(TarefaCategoriaController.class).categoria(tarefaResponse.getCategoriaId())).withRel("categoria")
                                                                                , linkTo(methodOn(UsuarioController.class).usuario(tarefaResponse.getUsuarioId())).withRel("usuario"));


        if(TarefaStatus.EM_ANDAMENTO.equals(tarefa.getStatus())){
            tarefaModel.add(
                    linkTo(methodOn(TarefaController.class).concluirTarefa(tarefa.getId())).withRel("concluir"),
                    linkTo(methodOn(TarefaController.class).cancelarTarefa(tarefa.getId())).withRel("cancelar")
                    );
        }

        if(TarefaStatus.ABERTO.equals(tarefa.getStatus())){
            tarefaModel.add(
                    linkTo(methodOn(TarefaController.class).iniciarTarefa(tarefa.getId())).withRel("iniciar"),
                    linkTo(methodOn(TarefaController.class).cancelarTarefa(tarefa.getId())).withRel("cancelar")
            );
        }

        return tarefaModel;
    }
}
