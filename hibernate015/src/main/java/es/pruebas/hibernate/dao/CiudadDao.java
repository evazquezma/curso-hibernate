package es.pruebas.hibernate.dao;

import java.util.List;

import es.pruebas.hibernate.model.Ciudad;

public interface CiudadDao {
	public List<Ciudad> getCiudadesConMasHabitantes(Integer numHabitantes);

	public List<Ciudad> getCiudadesMasAntiguas(Integer anhosAntiguedad);

	public List<Ciudad> getCiudadesSinAlcaldeCorrupto();

	public List<Ciudad> getCiudadesConAlcaldeCorruptoReelegido();

	public List<Ciudad> getCiudadesConMasMonumentos(Integer numMonumentos);
}
