package com.example.aluguelautomoveis.Model;

import javax.persistence.*;

@Entity
@Table(name = "agente")
public class Agente {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name = "usuario_id", nullable = false)
    private int usuarioID;

    public Agente() {

    }

    public Agente(Integer id, String rg, int usuarioID) {
        this.setId(id);
        this.setCnpj(rg);
        this.setUsuarioID(usuarioID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }
}
