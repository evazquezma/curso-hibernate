package es.pruebas.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.Direccion;
import es.pruebas.hibernate.model.Persona;

public class PersonaHQLDao implements PersonaDao {



	@Override
	public List<Persona> buscarPersonas(PersonaFiltro filtro) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		Criteria cri = session.createCriteria(Persona.class);

		if (filtro.getNombre() != null) {
			cri.add(Restrictions.ilike("nombre", filtro.getNombre(), MatchMode.ANYWHERE));
		}

		if (filtro.getNombrePadre() != null) {
			Criteria criPadre = cri.createCriteria("padre");
			criPadre.add(Restrictions.ilike("nombre", filtro.getNombrePadre(), MatchMode.ANYWHERE));
		}

		if (filtro.getDireccion() != null) {
			Criteria criDireccion = cri.createCriteria("direcciones");
			criDireccion.add(Restrictions.ilike("direccion", filtro.getDireccion(), MatchMode.ANYWHERE));
		}

		cri.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Persona> personas = cri.list();

		session.getTransaction().commit();
		return personas;
	}

	@Override
	public Direccion getUltimaDireccionPersona(Long idPersona) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		String queryString = "FROM Direccion d WHERE "
				+ " d.persona.id = :idPersona"
				+ " AND d.fecha = (SELECT MAX(d2.fecha) FROM Direccion d2 WHERE d2.persona = d.persona)";
		Query query = session.createQuery(queryString);
		query.setParameter("idPersona", idPersona);


		Direccion direccion = (Direccion) query.uniqueResult();

		session.getTransaction().commit();

		return direccion;
	}

	@Override
	public List<Persona> buscarPersonasSinPadres() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		String queryString = "FROM Persona WHERE padre = null";
		Query query = session.createQuery(queryString);

		List<Persona> personas = query.list();

		session.getTransaction().commit();

		return personas;
	}



	@Override
	public List<Persona> buscarPersonasSinHijos() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		String queryString = "FROM Persona p WHERE NOT EXISTS (SELECT id FROM Persona p2 where p2.padre = p)";
		Query query = session.createQuery(queryString);

		List<Persona> personas = query.list();

		session.getTransaction().commit();

		return personas;
	}

}
