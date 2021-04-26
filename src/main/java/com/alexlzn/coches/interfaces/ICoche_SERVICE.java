package com.alexlzn.coches.interfaces;

import java.util.List;

import com.alexlzn.coches.entities.Coche;
import com.alexlzn.coches.entities.Concesionario;

public interface ICoche_SERVICE {
	
	List<Coche> getCoches();
	Coche getCocheById(Integer id);
	List<Coche> getCochesByIdConcesionario(Integer idConcesionario);
	Concesionario addConcesionario(Concesionario concesionario);
	void deleteConcesionario(Integer idConcesionario);
	
}
