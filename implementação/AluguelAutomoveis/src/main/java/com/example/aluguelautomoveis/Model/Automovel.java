package com.example.aluguelautomoveis.Model;


import javax.persistence.*;

@Entity
@Table(name = "automovel")
public class Automovel {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ano", nullable = false)
    private int ano;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "modelo", nullable = false)
    private String modelo;

    @Column(name = "placa", nullable = false)
    private String placa;

    public Automovel() {

    }

    public Automovel(Integer id, int ano, String marca, String modelo, String placa) {
        setId(id);
        setAno(ano);
        setMarca(marca);
        setModelo(modelo);
        setPlaca(placa);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
