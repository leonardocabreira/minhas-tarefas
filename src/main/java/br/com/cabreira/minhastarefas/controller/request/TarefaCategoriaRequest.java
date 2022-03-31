package br.com.cabreira.minhastarefas.controller.request;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class TarefaCategoriaRequest {

    private Integer id;

    @NotBlank(message = "{categoria.nome.not-blank}")
    @Size(min = 5, max = 50, message = "{categoria.nome.size}")
    @Column(nullable = false, length = 50)
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
