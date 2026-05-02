package com.restaurante.model;


public class Cliente {

    private int    idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;


    public Cliente() {}


    public Cliente(String nombre, String apellido, String telefono, String email) {
        this.nombre    = nombre;
        this.apellido  = apellido;
        this.telefono  = telefono;
        this.email     = email;
    }


    public Cliente(int idCliente, String nombre, String apellido, String telefono, String email) {
        this(nombre, apellido, telefono, email);
        this.idCliente = idCliente;
    }


    public int    getIdCliente() { return idCliente; }
    public String getNombre()    { return nombre; }
    public String getApellido()  { return apellido; }
    public String getTelefono()  { return telefono; }
    public String getEmail()     { return email; }


    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public void setNombre(String nombre)    { this.nombre    = nombre; }
    public void setApellido(String apellido){ this.apellido  = apellido; }
    public void setTelefono(String telefono){ this.telefono  = telefono; }
    public void setEmail(String email)      { this.email     = email; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s %s | Tel: %s | Email: %s",
                idCliente, nombre, apellido, telefono, email);
    }
}
