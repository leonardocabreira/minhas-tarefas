package br.com.cabreira.minhastarefas.config;

import br.com.cabreira.minhastarefas.model.Tarefa;
import br.com.cabreira.minhastarefas.model.TarefaCategoria;
import br.com.cabreira.minhastarefas.model.Usuario;
import br.com.cabreira.minhastarefas.repository.TarefaCategoriaRepository;
import br.com.cabreira.minhastarefas.repository.TarefaRepository;
import br.com.cabreira.minhastarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TarefaCategoriaRepository categoriaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Bean
    CommandLineRunner executar(){
        return args -> {
            Usuario usuario = new Usuario();
            usuario.setNome("Leonardo");
            usuario.setSenha("12345678");
            usuarioRepository.save(usuario);

            TarefaCategoria categoria = new TarefaCategoria();
            categoria.setNome("Estudos");
            categoriaRepository.save(categoria);

            Tarefa tarefa = new Tarefa();
            tarefa.setDescricao("Aprender Spring Boot");
            tarefa.setDataEntrega(LocalDate.now().plusDays(1));
            tarefa.setVisivel(true);
            tarefa.setCategoria(categoria);
            tarefa.setUsuario(usuario);
            tarefaRepository.save(tarefa);

            Tarefa tarefa2 = new Tarefa();
            tarefa2.setDescricao("Testar SpringBoot");
            tarefa2.setDataEntrega(LocalDate.now().plusDays(2));
            tarefa2.setVisivel(true);
            tarefa2.setCategoria(categoria);
            tarefa2.setUsuario(usuario);
            tarefaRepository.save(tarefa2);

        };
    }
}
