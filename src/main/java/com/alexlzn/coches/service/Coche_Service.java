package com.alexlzn.coches.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alexlzn.coches.entities.Coche;
import com.alexlzn.coches.entities.Concesionario;
import com.alexlzn.coches.interfaces.ICoche_SERVICE;
@Service
public class Coche_Service implements ICoche_SERVICE {

	@Autowired
	private RestTemplate restTemplate;
	@Override
	public List<Coche> getCoches() {
		/*Lista de Concesionario llamo al endpoint del microservicio concesionario y como segundo argumento
		 * le paso un array de Concesionario[]. Luego esa lista la convierto a una lista de coches con stream y map
		 * y por cada variable "conce" voy añadiendo un coche y con collect lo convierto a una lista pero de Coche
		 */
		
		List<Concesionario> concesionario=  Arrays.asList(
				restTemplate.getForObject("http://localhost:8787/concesionarios", Concesionario[].class));
		return concesionario.stream().map(conce -> new Coche(conce)).collect(Collectors.toList());
	}

	@Override
	public Coche getCocheById(Integer id) {
		Map<String,String> mapa= new HashMap<String,String>();
		mapa.put("id", id.toString());
		Concesionario concesionario= restTemplate.
								getForObject("http://localhost:8787/concesionarios/{id}", Concesionario.class,mapa);
		return new Coche(concesionario);
	}

	@Override
	public List<Coche> getCochesByIdConcesionario(Integer id) {
		Map<String,String> mapa= new HashMap<String,String>();
		mapa.put("idconcesionario", id.toString());
		List<Coche> coches=Arrays.asList( restTemplate.getForObject("http://localhost:8787/cochesIdConcesionario/{idconcesionario}", Coche[].class,mapa));
		
		return coches;
				
	}

	@Override
	public Concesionario addConcesionario(Concesionario concesionario) {
		//metodo exchange de restTemplate (intercambiar) exchange(ruta, peticion, cuerpoJson,clase a la que hace referencia)
		//RequestBody es lo que le pase por el cuerpo del json de la peticion post
		//el metodo exchange devuelve un ResponseEntity
		
		HttpEntity<Concesionario> cuerpoPeticionJSON= new HttpEntity<Concesionario>(concesionario);
		ResponseEntity<Concesionario> responseEntity= restTemplate.exchange("http://localhost:8787/crearConcesionario", HttpMethod.POST, cuerpoPeticionJSON, Concesionario.class);
		Concesionario concesionarioResponse= responseEntity.getBody();
		return concesionarioResponse;
	}

	@Override
	public void deleteConcesionario(Integer idConcesionario) {
		//Eliminando un concesionario 
		Map<String,String> uriVariables= new HashMap<String,String>();
		uriVariables.put("id", idConcesionario.toString());
		restTemplate.delete("http://localhost:8787/borrarConcesionario/{id}", uriVariables); 
		
	}

}
