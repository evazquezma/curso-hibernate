package es.pruebas.hibernate.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.pruebas.hibernate.dao.FacturaDao;
import es.pruebas.hibernate.model.Factura;

@Service("facturaService")
@Transactional
public class FacturaServiceImpl implements FacturaService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FacturaDao facturaDao;




	@Override
	public Factura get(Long id) {
		Factura factura =  facturaDao.get(id);
		return factura;
	}


	@Override
	public void guardarValidando(Factura factura) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    Validator validator = factory.getValidator();

	    Set<ConstraintViolation<Factura>> constraintViolations = validator.validate(factura);
		for (ConstraintViolation<Factura> constraintViolation : constraintViolations) {
			logger.error(constraintViolation.toString());
		}

		if (! constraintViolations.isEmpty()) {
			throw new RuntimeException("Hay errores");
		}

		facturaDao.save(factura);
	}


	@Override
	public void guardarSinValidar(Factura factura) {
		facturaDao.save(factura);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Factura> getAll() {
		return facturaDao.getAll();
	}



}
