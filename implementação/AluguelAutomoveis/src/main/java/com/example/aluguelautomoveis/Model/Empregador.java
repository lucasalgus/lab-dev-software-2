package com.example.aluguelautomoveis.Model;

import javax.persistence.*;

@Entity
@Table(name = "empregador")
public class Empregador {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cliente_id")
    private int clienteID;

    public Empregador() {

    }

    public Empregador(Integer id, String nome, int clienteID) {
        setId(id);
        setNome(nome);
        setClienteID(clienteID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clientID) {
        this.clienteID = clientID;
    }
}
