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


	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> create(@RequestBody Estudiante estudiante) {

        if(estudiante != null && estudiante.getCedula() != null) {
        estudianteService.crear(estudiante);
            return new ResponseEntity<>("Se matriculó correctamente el estudiante: " + estudiante.toString(), HttpStatus.OK);
        }else{  
                System.out.println("Datos mal enviados por el cliente");
                return new ResponseEntity<>("Datos mal enviados por el cliente", HttpStatus.BAD_REQUEST);
        }
		

        
    }



}
