package com.example.aluguelautomoveis.Model;

import javax.persistence.*;

@Entity
@Table(name = "rendimento")
public class Rendimento {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "cliente_id", nullable = false)
    private int clienteID;

    public Rendimento() {

    }

    public Rendimento(Integer id, double valor, int clienteID) {
        setId(id);
        setValor(valor);
        setClienteID(clienteID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }
}
