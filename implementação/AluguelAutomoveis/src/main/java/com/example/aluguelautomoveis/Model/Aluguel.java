package com.example.aluguelautomoveis.Model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "aluguel")
public class Aluguel {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "contrato")
    private String contrato;

    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    @Column(name = "data_inicio", nullable = false)
    private Date dataInicio;

    @Column(name = "data_fim", nullable = false)
    private Date dataFim;

    @Column(name = "automovel_id", nullable = false)
    private int automovelID;

    @Column(name = "cliente_id", nullable = false)
    private int clienteID;

    @Column(name = "agente_id")
    private int agenteID;

    @Column(name = "proprietario_id")
    private int proprietarioID;

    public Aluguel() {

    }

    public Aluguel(Integer id, String contrato, boolean ativo, Date dataInicio, Date dataFim, int automovelID, int clienteID, int agenteID, int proprietarioID) {
        setId(id);
        setContrato(contrato);
        setAtivo(ativo);
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setAutomovelID(automovelID);
        setClienteID(clienteID);
        setAgenteID(agenteID);
        setProprietarioID(proprietarioID);
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

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public int getAutomovelID() {
        return automovelID;
    }

    public void setAutomovelID(int automovelID) {
        this.automovelID = automovelID;
    }

    public int getClienteID() {
        return clienteID;
    }

    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }

    public int getAgenteID() {
        return agenteID;
    }

    public void setAgenteID(int agenteID) {
        this.agenteID = agenteID;
    }

    public int getProprietarioID() {
        return proprietarioID;
    }

    public void setProprietarioID(int proprietarioID) {
        this.proprietarioID = proprietarioID;
    }
}
