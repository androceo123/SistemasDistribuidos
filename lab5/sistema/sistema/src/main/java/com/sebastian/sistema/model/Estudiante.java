package com.sebastian.sistema.model;


public class Estudiante {
    
    private Integer cedula;
    private String nombre;
    private String telefono;
    private String seccion;
    private Integer montoCuota;
    private String nivelEducacion;
    private Integer grado;

    protected Estudiante() {}

    public Estudiante(Integer cedula, String nombre, String telefono, String seccion, Integer monto, Integer grado, String nivel) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.seccion = seccion;
        this.montoCuota = monto;
        this.grado = grado;
        this.nivelEducacion = nivel;
      }

      @Override
      public String toString() {
        return String.format(
            "Persona[cedula=%d, nombre='%s']",
            cedula, nombre);
      }

      public Integer getCedula() {
        return cedula;
      }

      
}
