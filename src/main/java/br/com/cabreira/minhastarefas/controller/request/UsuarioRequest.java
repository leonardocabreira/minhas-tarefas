package br.com.cabreira.minhastarefas.controller.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    private Integer id;

    @NotBlank(message = "{usuario.nome.not-blank}")
    private String nome;

    @NotBlank(message = "{usuario.senha.not-blank}")
    @Size(min=8, max=64, message = "{usuario.senha.size}")
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

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
