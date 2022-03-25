package br.com.cabreira.minhastarefas.model;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="Tarefas")
@NamedQuery(name="Tarefa.tarefasPorUsuario", query = "select t from Tarefa t inner join t.usuario u where u.nome = ?1")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Size(min=5, max=150, message="O Campo descrição deve ter entre 5 e 150 caracteres")
    @NotBlank(message = "Campo descrição não pode estar vazio")
    @Column(nullable = false, length = 150)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TarefaStatus status;

    @FutureOrPresent(message = "Data de entrada deve ser maior ou iguala data atual")
    private LocalDate dataDeEntrega;

    private boolean visivel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TarefaCategoria categoria;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TarefaStatus getStatus() {
        return status;
    }

    public void setStatus(TarefaStatus status) {
        this.status = status;
    }

    public LocalDate getDataDeEntrega() {
        return dataDeEntrega;
    }

    public void setDataDeEntrega(LocalDate dataDeEntrega) {
        this.dataDeEntrega = dataDeEntrega;
    }

    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public TarefaCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TarefaCategoria categoria) {
        this.categoria = categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
