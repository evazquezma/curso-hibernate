package es.pruebas.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.hibernate.model.Agregado;
import es.pruebas.hibernate.model.TipoFactura;

@Repository("agregadoDao")
public class AgregadoDaoImpl implements AgregadoDao {
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	@Autowired
	public AgregadoDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public Agregado getPorTipoFactura(TipoFactura tipo) {
		return (Agregado) getSession().get(Agregado.class, tipo);
	}


	@Override
	public void save(Agregado agregado) {
		getSession().save(agregado);

	}

	@Override
	public List<Agregado> getAll() {
		return getSession().createCriteria(Agregado.class).list();
	}



}
