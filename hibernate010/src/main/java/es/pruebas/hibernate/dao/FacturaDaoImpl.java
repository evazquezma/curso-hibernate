package es.pruebas.hibernate.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.hibernate.model.Factura;

@Repository("facturaDao")
public class FacturaDaoImpl implements FacturaDao {
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Autowired
	public FacturaDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public Factura get(Long id) {
		return (Factura) getSession().get(Factura.class, id, LockOptions.READ);
	}


	@Override
	public void save(Factura factura) {
		getSession().save(factura);

	}

	@Override
	public List<Factura> getAll() {
		return getSession().createCriteria(Factura.class).list();
	}


}
