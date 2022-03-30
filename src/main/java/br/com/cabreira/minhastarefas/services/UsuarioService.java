package br.com.cabreira.minhastarefas.services;

import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import br.com.cabreira.minhastarefas.model.Usuario;
import br.com.cabreira.minhastarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repositorio;

    public List<Usuario> getTodosUsuarios(){
        return repositorio.findAll();
    }

    public List<Usuario> getUsuarioPorNome(String nome){
        return repositorio.findByNomeLike("%"+nome+"%");
    }

    public Usuario getUsuarioPorId(Integer id){
        return repositorio.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    public Usuario salvarUsuario(Usuario usuario){
        return repositorio.save(usuario);
    }

    public void deleteUsuarioPorId(Integer id){
        repositorio.deleteById(id);
    }
}
