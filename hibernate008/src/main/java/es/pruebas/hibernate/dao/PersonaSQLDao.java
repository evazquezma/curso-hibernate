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

public class PersonaSQLDao implements PersonaDao {



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

		String queryString = "Select * FROM DIRECCION d WHERE "
				+ " d.idPersona = :idPersona"
				+ " AND d.fecha = (SELECT MAX(d2.fecha) FROM DIRECCION d2 WHERE d2.idPersona = d.idPersona)";
		Query query = session.createSQLQuery(queryString).addEntity(Direccion.class);
		query.setParameter("idPersona", idPersona);


		Direccion direccion = (Direccion) query.uniqueResult();

		session.getTransaction().commit();

		return direccion;
	}

	@Override
	public List<Persona> buscarPersonasSinPadres() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		String queryString = "Select * FROM PERSONA WHERE idPadre = null";
		Query query = session.createSQLQuery(queryString).addEntity(Persona.class);

		List<Persona> personas = query.list();

		session.getTransaction().commit();

		return personas;
	}



	@Override
	public List<Persona> buscarPersonasSinHijos() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		String queryString = "Select p.* FROM PERSONA p WHERE NOT EXISTS (SELECT id FROM PERSONA p2 where p2.idPadre = p.id)";
		Query query = session.createSQLQuery(queryString).addEntity(Persona.class);;

		List<Persona> personas = query.list();

		session.getTransaction().commit();

		return personas;
	}

}
