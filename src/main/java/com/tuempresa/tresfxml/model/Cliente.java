package com.tuempresa.tresfxml.model;

public class Cliente {
    private String nombre;
    private String correo;
    private String Telefono;
    private String tipoCLiente;
    private String identicacion;
    private String directorioCliente;

    public Cliente(){

    }

    public Cliente(String nombre, String correo, String telefono, String tipoCLiente, String identicacion, String directorioCliente) {
        this.nombre = nombre;
        this.correo = correo;
        Telefono = telefono;
        this.tipoCLiente = tipoCLiente;
        this.identicacion = identicacion;
        this.directorioCliente = directorioCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getTipoCLiente() {
        return tipoCLiente;
    }

    public void setTipoCLiente(String tipoCLiente) {
        this.tipoCLiente = tipoCLiente;
    }

    public String getIdenticacion() {
        return identicacion;
    }

    public void setIdenticacion(String identicacion) {
        this.identicacion = identicacion;
    }

    public String getDirectorioCliente() {
        return directorioCliente;
    }

    public void setDirectorioCliente(String directorioCliente) {
        this.directorioCliente = directorioCliente;
    }
}
