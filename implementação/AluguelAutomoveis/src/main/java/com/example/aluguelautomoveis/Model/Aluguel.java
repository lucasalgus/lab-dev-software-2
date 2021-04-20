package com.example.aluguelautomoveis.Model;


import javax.persistence.*;

@Entity
@Table(name = "aluguel")
public class Aluguel {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "contrato", nullable = false)
    private String contrato;

    @Column(name = "proprietario", nullable = false)
    private int proprietarioID;

    @Column(name = "agente", nullable = false)
    private int agenteID;

    @Column(name = "automovel", nullable = false)
    private int automovelID;

    public Aluguel() {

    }

    public Aluguel(Integer id, String contrato, int proprietarioID, int agenteID, int automovelID) {
        this.id = id;
        this.contrato = contrato;
        this.proprietarioID = proprietarioID;
        this.agenteID = agenteID;
        this.automovelID = automovelID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public int getProprietarioID() {
        return proprietarioID;
    }

    public void setProprietarioID(int proprietarioID) {
        this.proprietarioID = proprietarioID;
    }

    public int getAgenteID() {
        return agenteID;
    }

    public void setAgenteID(int agenteID) {
        this.agenteID = agenteID;
    }

    public int getAutomovelID() {
        return automovelID;
    }

    public void setAutomovelID(int automovelID) {
        this.automovelID = automovelID;
    }
}
