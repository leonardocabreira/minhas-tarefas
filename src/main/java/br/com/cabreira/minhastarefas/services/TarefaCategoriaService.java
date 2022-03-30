package br.com.cabreira.minhastarefas.services;

import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import br.com.cabreira.minhastarefas.repository.TarefaCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TarefaCategoriaService {

    @Autowired
    private TarefaCategoriaRepository repositorio;

    public List<TarefaCategoria> getTodasCategorias(){
        return repositorio.findAll();
    }

    public List<TarefaCategoria> getCategoriaPorNome(String nome){
        return repositorio.findByNomeLike("%"+nome+"%");
    }

    public TarefaCategoria getCategoria(Integer id){
        return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public TarefaCategoria salvarCategoria(TarefaCategoria categoria){
        return repositorio.save(categoria);
    }

    public void deleteCategoriaPorId(Integer id){
        repositorio.deleteById(id);
    }

}
