package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.controller.assembler.UsuarioModelAssembler;
import br.com.cabreira.minhastarefas.controller.request.UsuarioRequest;
import br.com.cabreira.minhastarefas.controller.response.UsuarioResponse;
import br.com.cabreira.minhastarefas.model.Usuario;
import br.com.cabreira.minhastarefas.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioModelAssembler assembler;

    @GetMapping("/usuario")
    public CollectionModel<EntityModel<UsuarioResponse>> todosUsuarios(@RequestParam Map<String,String> parametros){
        List<Usuario> usuarios = new ArrayList<>();

        if(parametros.isEmpty()) {
            usuarios = service.getTodosUsuarios();
        }else {
            String descricao = parametros.get("nome");
            usuarios = service.getUsuarioPorNome(descricao);
        }
        List<EntityModel<UsuarioResponse>> usuariosModel = usuarios.stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(usuariosModel, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).todosUsuarios(new HashMap<>())).withSelfRel());
    }

    @GetMapping("/usuario/{id}")
    public EntityModel<UsuarioResponse> usuario(@PathVariable Integer id){
        final Usuario usuario = service.getUsuarioPorId(id);
        return assembler.toModel(usuario);
    }
    /*produces = JSON*/
    @PostMapping(value = "/usuario")
    public UsuarioResponse salvarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){
        Usuario usuario = mapper.map(usuarioRequest, Usuario.class);
        return mapper.map(service.salvarUsuario(usuario),UsuarioResponse.class);
    }

    @DeleteMapping("/usuario/{id}")
    public void excluirUsuario(@PathVariable Integer id){
        service.deleteUsuarioPorId(id);
    }

    }
