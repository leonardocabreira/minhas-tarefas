package br.com.cabreira.minhastarefas.controller;

import br.com.cabreira.minhastarefas.controller.request.TarefaCategoriaRequest;
import br.com.cabreira.minhastarefas.controller.response.TarefaCategoriaResponse;
import br.com.cabreira.minhastarefas.controller.response.TarefaResponse;
import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import br.com.cabreira.minhastarefas.repository.TarefaCategoriaRepository;
import br.com.cabreira.minhastarefas.services.TarefaCategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TarefaCategoriaController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TarefaCategoriaService service;

    @GetMapping("/categoria")
    public List<TarefaCategoriaResponse> todasCategorias(@RequestParam Map<String,String> parametros){
        List<TarefaCategoria> categorias = new ArrayList<>();
        if(parametros.isEmpty()){
            categorias = service.getTodasCategorias();
        }else{
            categorias = service.getCategoriaPorNome(parametros.get("nome"));
        }

        final List<TarefaCategoriaResponse> tarefasCategoriaResponse = categorias.stream().map(categoria -> mapper.map(categoria, TarefaCategoriaResponse.class)).collect(Collectors.toList());
        return tarefasCategoriaResponse;
    }

    @GetMapping("/categoria/{id}")
    public TarefaCategoriaResponse categoria(@PathVariable Integer id){
        return mapper.map(service.getCategoria(id), TarefaCategoriaResponse.class);
    }

    @PostMapping("/categoria")
    public TarefaCategoriaResponse salvarCategoria(@Valid @RequestBody TarefaCategoriaRequest tarefaCategoriaRequest) {
        final TarefaCategoria tarefaCategoria = mapper.map(tarefaCategoriaRequest, TarefaCategoria.class);
        return mapper.map(service.salvarCategoria(tarefaCategoria), TarefaCategoriaResponse.class);
    }

    @DeleteMapping("/categoria/{id}")
    public void excluirCategoria(@PathVariable Integer id){
        service.deleteCategoriaPorId(id);
    }

}
