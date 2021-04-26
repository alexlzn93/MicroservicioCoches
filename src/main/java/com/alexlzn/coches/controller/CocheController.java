package com.alexlzn.coches.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexlzn.coches.entities.Coche;
import com.alexlzn.coches.entities.Concesionario;
import com.alexlzn.coches.service.Coche_Service;

@RestController
public class CocheController {

	@Autowired
	private Coche_Service cocheService;
	
	@GetMapping("/coches")
	public List<Coche> getListCoches(){
		return cocheService.getCoches();
	}
	@GetMapping("/coches/{id}")
	public Coche getCocheByID(@PathVariable Integer id){
		return cocheService.getCocheById(id);
	}
	
	@GetMapping("/cochesByIdConcesionario/{idConcesionario}")
	public List<Coche> getCocheByIDConcesionario(@PathVariable("idConcesionario") Integer id){
		return cocheService.getCochesByIdConcesionario(id);
	}
	
	@PostMapping("/crearConcesionario")
	public Concesionario crearConcesionario(@RequestBody Concesionario concesionario) {
		return cocheService.addConcesionario(concesionario);
	}
	
	@DeleteMapping("/eliminarConcesionario/{id}")
	public void eliminarConcesionario(@PathVariable("id") Integer  idconcesionario) {
		 cocheService.deleteConcesionario(idconcesionario);
	}
}
