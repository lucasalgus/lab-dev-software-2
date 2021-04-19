package com.example.aluguelautomoveis.Model;

import javax.persistence.*;

@Entity
@Table(name = "administrador")
public class Administrador {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "usuario_id", nullable = false)
    private int usuarioID;

    public Administrador() {

    }

    public Administrador(Integer id, Integer usuarioID) {
        this.setId(id);
        this.setUsuarioID(usuarioID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }
}
