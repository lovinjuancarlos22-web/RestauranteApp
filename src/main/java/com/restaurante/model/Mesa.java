package com.restaurante.model;


public class Mesa {

    public enum Estado { libre, ocupada, reservada }

    private int    idMesa;
    private int    numeroMesa;
    private int    capacidad;
    private Estado estado;

    public Mesa() {}

    public Mesa(int numeroMesa, int capacidad) {
        this.numeroMesa = numeroMesa;
        this.capacidad  = capacidad;
        this.estado     = Estado.libre;
    }

    public Mesa(int idMesa, int numeroMesa, int capacidad, String estado) {
        this(numeroMesa, capacidad);
        this.idMesa = idMesa;
        this.estado = Estado.valueOf(estado);
    }

    public int    getIdMesa()     { return idMesa; }
    public int    getNumeroMesa() { return numeroMesa; }
    public int    getCapacidad()  { return capacidad; }
    public Estado getEstado()     { return estado; }

    public void setIdMesa(int idMesa)         { this.idMesa     = idMesa; }
    public void setNumeroMesa(int numeroMesa) { this.numeroMesa = numeroMesa; }
    public void setCapacidad(int capacidad)   { this.capacidad  = capacidad; }
    public void setEstado(Estado estado)      { this.estado     = estado; }
    public void setEstado(String estado)      { this.estado     = Estado.valueOf(estado); }

    public boolean estaLibre() {
        return estado == Estado.libre;
    }

    @Override
    public String toString() {
        return String.format("Mesa %d | Capacidad: %d | Estado: %s",
                numeroMesa, capacidad, estado);
    }
}
