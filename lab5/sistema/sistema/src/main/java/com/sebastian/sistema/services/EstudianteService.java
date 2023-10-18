package com.sebastian.sistema.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.sistema.model.Estudiante;

@Service
public class EstudianteService {

    public List<Estudiante> estudiantes = new ArrayList<Estudiante>();
  
    public List<Estudiante> getEstudiantes(){

        return estudiantes;
    }
    
    public boolean crear(Estudiante e){

        return estudiantes.add(e);
    }

}
