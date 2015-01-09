package es.pruebas.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.hibernate.dao.FacturaDao;
import es.pruebas.hibernate.model.Factura;

@Service("facturaService")
@Transactional
public class FacturaServiceImpl implements FacturaService {
	@Autowired
	private FacturaDao facturaDao;




	@Override
	public Factura get(Long id) {
		Factura factura =  facturaDao.get(id);
		return factura;
	}


	@Override
	public void guardarFactura(Factura factura) {
		facturaDao.save(factura);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Factura> getAll() {
		return facturaDao.getAll();
	}



}
