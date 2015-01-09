package es.pruebas.hibernate.dao;

import java.util.List;

import es.pruebas.hibernate.model.Alcalde;

public interface AlcaldeDao {
	public List<Alcalde> getAlcaldesMonumentoMasAntiguo(Integer antiguedadMonumento);
}
