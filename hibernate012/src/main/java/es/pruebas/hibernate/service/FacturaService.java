package es.pruebas.hibernate.service;

import java.util.List;

import es.pruebas.hibernate.model.Factura;

public interface FacturaService {

	public Factura get(Long id);

	public void guardarValidando(Factura factura);

	public void guardarSinValidar(Factura factura);

	public List<Factura> getAll();


}
