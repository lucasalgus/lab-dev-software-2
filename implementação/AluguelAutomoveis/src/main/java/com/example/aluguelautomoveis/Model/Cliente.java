package com.example.aluguelautomoveis.Model;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rg", nullable = false)
    private String rg;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "profissao", nullable = false)
    private String profissao;

    @Column(name = "usuario_id", nullable = false)
    private int usuarioID;

    public Cliente() {

    }

    public Cliente(Integer id, String rg, String cpf, String profissao, int usuarioID) {
        setId(id);
        setRg(rg);
        setCpf(cpf);
        setProfissao(profissao);
        setUsuarioID(usuarioID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }
}
