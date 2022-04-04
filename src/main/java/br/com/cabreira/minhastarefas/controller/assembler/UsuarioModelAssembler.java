package br.com.cabreira.minhastarefas.controller.assembler;

import br.com.cabreira.minhastarefas.controller.UsuarioController;
import br.com.cabreira.minhastarefas.controller.response.UsuarioResponse;
import br.com.cabreira.minhastarefas.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<UsuarioResponse>> {

    @Autowired
    private ModelMapper mapper;

    @Override
    public EntityModel<UsuarioResponse> toModel(Usuario Usuario) {
        UsuarioResponse UsuarioResponse = mapper.map(Usuario, UsuarioResponse.class);
        EntityModel<UsuarioResponse> UsuarioModel = EntityModel.of(UsuarioResponse, linkTo(methodOn(UsuarioController.class).usuario(UsuarioResponse.getId())).withSelfRel()
                , linkTo(methodOn(UsuarioController.class).todosUsuarios(new HashMap<>())).withRel("usuarios")
        );

        return UsuarioModel;
    }
}