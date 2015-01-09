package es.pruebas.hibernate.dao;

import java.util.List;

import es.pruebas.hibernate.model.Direccion;
import es.pruebas.hibernate.model.Persona;

public interface PersonaDao {
	public List<Persona> buscarPersonas(PersonaFiltro filtro);

	public Direccion getUltimaDireccionPersona(Long idPersona);

	public List<Persona> buscarPersonasSinPadres();

	public List<Persona> buscarPersonasSinHijos();
}
