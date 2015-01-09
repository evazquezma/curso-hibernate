package es.pruebas.hibernate.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.Direccion;
import es.pruebas.hibernate.model.Persona;

public class PersonaCriteriaDao implements PersonaDao {



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

		Criteria cri = session.createCriteria(Direccion.class, "direccion");

		DetachedCriteria maxFecha = DetachedCriteria.forClass(Direccion.class, "direccion2");
		maxFecha.setProjection(Property.forName("direccion2.fecha").max());
		maxFecha.add(Property.forName("direccion2.persona.id").eqProperty("direccion.persona.id"));
		cri.add(Property.forName("fecha").eq(maxFecha));

		Criteria criPersona = cri.createCriteria("persona");
		criPersona.add(Restrictions.idEq(idPersona));

		Direccion direccion = (Direccion) cri.uniqueResult();

		session.getTransaction().commit();

		return direccion;
	}

	@Override
	public List<Persona> buscarPersonasSinPadres() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();


		Criteria cri = session.createCriteria(Persona.class);
		cri.add(Restrictions.isNull("padre"));

		List<Persona> personas = cri.list();

		session.getTransaction().commit();

		return personas;
	}



	@Override
	public List<Persona> buscarPersonasSinHijos() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();


		Criteria cri = session.createCriteria(Persona.class, "persona");
		DetachedCriteria personaDos = DetachedCriteria.forClass(Persona.class, "persona2")
	            .add(Property.forName("persona2.padre.id").eqProperty("persona.id"))
	            .setProjection(Projections.property("id"));
		cri.add(Subqueries.notExists(personaDos));


		List<Persona> personas = cri.list();

		session.getTransaction().commit();

		return personas;
	}

}
