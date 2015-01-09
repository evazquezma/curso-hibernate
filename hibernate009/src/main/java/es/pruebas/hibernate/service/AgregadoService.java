package es.pruebas.hibernate.service;

import java.util.List;

import es.pruebas.hibernate.model.Agregado;
import es.pruebas.hibernate.model.Factura;

public interface AgregadoService {
	public void save(Agregado agregado);

	public void sumarFactura(Factura factura);

	public List<Agregado> getAll();


}
