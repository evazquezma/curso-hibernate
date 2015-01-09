package es.pruebas.hibernate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.hibernate.dao.AgregadoDao;
import es.pruebas.hibernate.model.Agregado;
import es.pruebas.hibernate.model.Factura;

@Service("agregadoService")
@Transactional
public class AgregadoServiceImpl implements AgregadoService {
	@Autowired
	private AgregadoDao agregadoDao;

	@Override
	//Poniendo esto, no guarda en base de datos @Transactional(readOnly=true)
	public void save(Agregado agregado) {
		agregadoDao.save(agregado);
	}


	@Override
	//@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void sumarFactura(Factura factura) {
		Agregado agregado = agregadoDao.getPorTipoFactura(factura.getTipo());
		agregado.setNumeroFacturas(agregado.getNumeroFacturas() + 1);
		agregado.setTotal(agregado.getTotal() + factura.getValor());

		agregadoDao.save(agregado);
	}




	@Override
	@Transactional(readOnly=true)
	public List<Agregado> getAll() {
		return agregadoDao.getAll();
	}






}
