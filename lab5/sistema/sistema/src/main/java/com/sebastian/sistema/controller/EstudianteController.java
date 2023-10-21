package com.sebastian.sistema.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.sistema.model.Estudiante;
import com.sebastian.sistema.services.EstudianteService;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

	@Autowired
	EstudianteService estudianteService = new EstudianteService();

    @GetMapping(value = "/listar", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<List<Estudiante>> getEstudiantes() 
	{
		
		List<Estudiante> r = estudianteService.getEstudiantes();

		return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @RequestMapping(value = "/{cedula}/grado", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method=RequestMethod.GET )
    public ResponseEntity<String> getGrado(@PathVariable("cedula") String cedula){

        Integer cedulaint = Integer.parseInt(cedula);
        Estudiante e = estudianteService.getEstudiante(cedulaint);

        if(e != null){
            return new ResponseEntity<>("El estudiante " + e.getNombre() + " está en el grado " + e.getGrado(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se encontró el estudiante", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{cedula}/nivel", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method=RequestMethod.GET )
    public ResponseEntity<String> getNivel(@PathVariable("cedula") String cedula){

        Integer cedulaint = Integer.parseInt(cedula);
        Estudiante e = estudianteService.getEstudiante(cedulaint);

        if(e != null){
            return new ResponseEntity<>("El estudiante " + e.getNombre() + " está en " + e.getNivelEducacion(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se encontró el estudiante", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{cedula}/seccion", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method=RequestMethod.POST )
    public ResponseEntity<String> setSeccion(@PathVariable("cedula") String cedula){

        Integer cedulaint = Integer.parseInt(cedula);
        Estudiante e = estudianteService.getEstudiante(cedulaint);

        if(e != null){
            estudianteService.AsignarSeccion(e);
            return new ResponseEntity<>("Sección asignada correctamente:" + e.getSeccion() , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("No se encontró el estudiante", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{cedula}/cuota", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method=RequestMethod.POST )
    public ResponseEntity<String> setCuota(@PathVariable("cedula") String cedula, @RequestBody Integer monto){

        Integer cedulaint = Integer.parseInt(cedula);
        Estudiante e = estudianteService.getEstudiante(cedulaint);

        if(e != null){

            if(estudianteService.CalcularCuota(e, monto)){
                return new ResponseEntity<>("La cuota actual es de " + e.getMontoCuota() , HttpStatus.OK);
            }else{
                return new ResponseEntity<>("No se pudo calcular la cuota" , HttpStatus.BAD_REQUEST);
            }
            
            
        }else{
            return new ResponseEntity<>("No se encontró el estudiante", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{cedula}/cuotareset", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, method=RequestMethod.POST )
    public ResponseEntity<String> resetCuota(@PathVariable("cedula") String cedula){

        Integer cedulaint = Integer.parseInt(cedula);
        Estudiante e = estudianteService.getEstudiante(cedulaint);

        if(e != null){

            e.setMontoCuota(500000);
            return new ResponseEntity<>("La cuota actual es de " + e.getMontoCuota() , HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>("No se encontró el estudiante", HttpStatus.NOT_FOUND);
        }
    }

    
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Estudiante estudiante) {

        if(estudiante != null && estudiante.getCedula() != null) {
            
            if(estudianteService.crear(estudiante)){
                System.out.println("Se matriculó correctamente el estudiante: " + estudiante.toString());

                return new ResponseEntity<>("Se matriculó correctamente el estudiante: " + estudiante.toString(), HttpStatus.OK);
            }else{
                System.out.println("No se pudo matricular el estudiante: " + estudiante.toString());

                return new ResponseEntity<>("No se pudo matricular el estudiante: " + estudiante.toString(), HttpStatus.BAD_REQUEST);
            }
        }else{  
                System.out.println("Datos mal enviados por el cliente");
                return new ResponseEntity<>("Datos mal enviados por el cliente", HttpStatus.BAD_REQUEST);
        }
		

        
    }

}
