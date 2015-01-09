package es.pruebas.hibernate.dao;

import java.util.List;

import es.pruebas.hibernate.model.Agregado;
import es.pruebas.hibernate.model.TipoFactura;

public interface AgregadoDao {
	public void save(Agregado agregado);

	public List<Agregado> getAll();

	public Agregado getPorTipoFactura(TipoFactura tipo);
}
