package br.com.cabreira.minhastarefas.controller.request;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class TarefaRequest {

    private Integer id;

    @NotBlank(message = "{tarefa.descricao.not-blank}")
    @Size(min=5, max=150, message="{tarefa.descricao.size}")
    @Column(nullable = false, length = 150)
    private String descricao;

    @NotNull
    @FutureOrPresent(message = "{tarefa.descricao.future-or-present}")
    private LocalDate dataEntrega;

    @NotNull(message = "{tarefa.categoria.not-null}")
    @Min(value = 1, message = "{tarefa.categoria.min}")
    private Integer categoriaId;

    @NotNull(message = "{tarefa.usuario.not-null}")
    @Min(value = 1, message = "{tarefa.usuario.min}")
    private Integer usuarioId;

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

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Integer categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioID) {
        this.usuarioId = usuarioID;
    }
}
