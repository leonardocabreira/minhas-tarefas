package br.com.cabreira.minhastarefas.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name="Tarefas")
@NamedQuery(name="Tarefa.tarefasPorUsuario", query = "select t from Tarefa t inner join t.usuario u where u.nome = ?1")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "{tarefa.descricao.not-blank}")
    @Size(min=5, max=150, message="{tarefa.descricao.size}")
    @Column(nullable = false, length = 150)
    private String descricao;

    @Enumerated(EnumType.STRING)
    private TarefaStatus status = TarefaStatus.ABERTO;

    @NotNull
    @FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
    private LocalDate dataEntrega;

    private boolean visivel;

    @NotNull(message = "{tarefa.categoria.not-null}")
    @ManyToOne
    @JoinColumn(nullable = false)
    private TarefaCategoria categoria;

    @NotNull(message = "{tarefa.usuario.not-null}")
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

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
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
