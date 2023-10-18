package com.sebastian.sistema.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        if(!validarNivel(e.getNivelEducacion())){
            return false;
        }

        if(!validarGrado(e.getGrado(), e.getNivelEducacion())){
            return false;
        }


        return estudiantes.add(e);
    }

    public boolean validarNivel(String nivel){

        if(nivel.equals("primaria") || nivel.equals("secundaria")){
            return true;
        }else{
            return false;
        }
    }

    public boolean validarGrado(Integer grado, String nivel){
        if(nivel.equals("primaria")){
            if(grado >= 1 && grado <= 9){
                return true;
            }else{
                return false;
            }
        }else if(nivel.equals("secundaria")){
            if(grado >= 1 && grado <= 3){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public Estudiante getEstudiante(Integer cedula){
        for(Estudiante e : estudiantes){
            if(e.getCedula().equals(cedula)){
                return e;
            }
        }

        return null;
    }

    public boolean AsignarSeccion(Estudiante e){
        Random rand = new Random();
        int int_random = rand.nextInt(2);

        if(int_random == 0){
            e.setSeccion("M");
        }else{
            e.setSeccion("T");
        }
        return true;
    }

    public boolean CalcularCuota(Estudiante e, Integer monto){
        Integer actual = e.getMontoCuota();

        if(monto <= 0){
            return false;
        }

        if(monto > actual){
            return false;
        }

        e.setMontoCuota(actual - monto);
        return true;
    }
}

