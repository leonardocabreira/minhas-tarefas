package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.controller.request.UsuarioRequest;
import br.com.cabreira.minhastarefas.controller.response.UsuarioResponse;
import br.com.cabreira.minhastarefas.model.Usuario;
import br.com.cabreira.minhastarefas.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class UsuarioController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsuarioService service;

    @GetMapping("/usuario")
    public List<UsuarioResponse> todosUsuarios(@RequestParam Map<String,String> parametros){
        List<Usuario> usuarios = new ArrayList<>();

        if(parametros.isEmpty()) {
            usuarios = service.getTodosUsuarios();
        }else {
            String descricao = parametros.get("nome");
            usuarios = service.getUsuarioPorNome(descricao);
        }
        final List<UsuarioResponse> usuarioResponse = usuarios.stream().map(usuario -> mapper.map(usuario, UsuarioResponse.class)).collect(Collectors.toList());
        return usuarioResponse;
    }

    @GetMapping("/usuario/{id}")
    public UsuarioResponse usuario(@PathVariable Integer id){
        final Usuario usuario = service.getUsuarioPorId(id);
        return mapper.map(usuario, UsuarioResponse.class);
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
