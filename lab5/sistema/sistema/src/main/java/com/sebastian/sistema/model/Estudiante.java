package com.sebastian.sistema.model;


public class Estudiante {
    
    private Integer cedula;
    private String nombre;
    private String telefono;
    private String seccion;
    private Integer montoCuota = 500000;
    private String nivelEducacion;
    private Integer grado;

    protected Estudiante() {}

    public Estudiante(Integer cedula, String nombre, String telefono, Integer grado, String nivel) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.grado = grado;
        this.nivelEducacion = nivel;
      }

      @Override
      public String toString() {
        return String.format(
            "Persona cedula=%d, nombre='%s'",
            cedula, nombre);
      }

      public Integer getCedula() {
        return cedula;
      }

      public void setCedula(Integer cedula) {
        this.cedula = cedula;
      }
      
      public String getNombre() {
        return nombre;
      }

      public void setNombre(String nombre) {
        this.nombre = nombre;
      }

      public String getTelefono() {
        return telefono;
      }

      public void setTelefono(String telefono) {
        this.telefono = telefono;
      }

      public String getSeccion() {
        return seccion;
      }

      public void setSeccion(String seccion) {
        this.seccion = seccion;
      }

      public Integer getMontoCuota() {
        return montoCuota;
      }

      public void setMontoCuota(Integer montoCuota) {
        this.montoCuota = montoCuota;
      }

      public Integer getGrado() {
        return grado;
      }

      public void setGrado(Integer grado) {
        this.grado = grado;
      }

      public String getNivelEducacion() {
        return nivelEducacion;
      }

      public void setNivelEducacion(String nivelEducacion) {
        this.nivelEducacion = nivelEducacion;
      }


}
