package es.pruebas.hibernate.dao;

import java.util.List;

import es.pruebas.hibernate.model.Factura;

public interface FacturaDao {
	public void save(Factura alumno);

	public List<Factura> getAll();

	public Factura get(Long id);
}
