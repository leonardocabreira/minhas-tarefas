package br.com.cabreira.minhastarefas.model;

import javax.persistence.*;

@Entity
@Table(name="Roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EnumRole nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EnumRole getNome() {
        return nome;
    }

    public void setNome(EnumRole nome) {
        this.nome = nome;
    }
}
