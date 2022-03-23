package br.com.cabreira.minhastarefas.model;

import javax.persistence.*;

@Entity
public class TarefaCategoria {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
