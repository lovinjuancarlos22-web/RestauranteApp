package com.restaurante.model;


public class Plato {

    private int     idPlato;
    private int     idCategoria;
    private String  nombreCategoria;
    private String  nombre;
    private String  descripcion;
    private double  precio;
    private boolean disponible;

    public Plato() {}

    public Plato(int idCategoria, String nombre, String descripcion, double precio) {
        this.idCategoria = idCategoria;
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.precio      = precio;
        this.disponible  = true;
    }

    public Plato(int idPlato, int idCategoria, String nombreCategoria,
                 String nombre, String descripcion, double precio, boolean disponible) {
        this(idCategoria, nombre, descripcion, precio);
        this.idPlato         = idPlato;
        this.nombreCategoria = nombreCategoria;
        this.disponible      = disponible;
    }

    public int     getIdPlato()          { return idPlato; }
    public int     getIdCategoria()      { return idCategoria; }
    public String  getNombreCategoria()  { return nombreCategoria; }
    public String  getNombre()           { return nombre; }
    public String  getDescripcion()      { return descripcion; }
    public double  getPrecio()           { return precio; }
    public boolean isDisponible()        { return disponible; }

    public void setIdPlato(int idPlato)                    { this.idPlato         = idPlato; }
    public void setIdCategoria(int idCategoria)            { this.idCategoria     = idCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }
    public void setNombre(String nombre)                   { this.nombre          = nombre; }
    public void setDescripcion(String descripcion)         { this.descripcion     = descripcion; }
    public void setPrecio(double precio)                   { this.precio          = precio; }
    public void setDisponible(boolean disponible)          { this.disponible      = disponible; }

    @Override
    public String toString() {
        return String.format("[%d] %-30s | %s | %.2f€ | %s",
                idPlato, nombre, nombreCategoria, precio,
                disponible ? "Disponible" : "No disponible");
    }
}
