package es.pruebas.hibernate.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.hibernate.model.Ciudad;

@Repository("CiudadDao")
public class CiudadDaoImpl implements CiudadDao {

	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	@Autowired
	public CiudadDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	@Override
	public List<Ciudad> getCiudadesConMasHabitantes(Integer numHabitantes) {
		Criteria cri = getSession().createCriteria(Ciudad.class);
		cri.add(Restrictions.gt("poblacion", numHabitantes));
		return cri.list();
	}

	@Override
	public List<Ciudad> getCiudadesMasAntiguas(Integer anhosAntiguedad) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, anhosAntiguedad * -1);

		Criteria cri = getSession().createCriteria(Ciudad.class);
		cri.add(Restrictions.lt("fechaFundacion", cal.getTime()));

		return cri.list();
	}

	@Override
	public List<Ciudad> getCiudadesSinAlcaldeCorrupto() {
		Criteria cri = getSession().createCriteria(Ciudad.class);

		Criteria criAlcalde = cri.createCriteria("alcalde");
		criAlcalde.add(Restrictions.eq("juiciosPorCorrupcion", 0));

		cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cri.list();
	}

	@Override
	public List<Ciudad> getCiudadesConAlcaldeCorruptoReelegido() {
		Criteria cri = getSession().createCriteria(Ciudad.class);

		Criteria criAlcalde = cri.createCriteria("alcalde");
		criAlcalde.add(Restrictions.gt("juiciosPorCorrupcion", 0));
		criAlcalde.add(Restrictions.gt("numeroMandatos", 1));


		cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cri.list();
	}

	@Override
	public List<Ciudad> getCiudadesConMasMonumentos(Integer numMonumentos) {
		Criteria cri = getSession().createCriteria(Ciudad.class, "ciudadx");

		cri.add(Restrictions.sizeGt("monumentos", numMonumentos));

		/*
		//Que exista monumento
		DetachedCriteria existMonumentosSuquery = DetachedCriteria.forClass(Monumento.class , "monumento");
		existMonumentosSuquery.add( Property.forName("monumento.ciudad.id").eqProperty("ciudadx.id"));
		existMonumentosSuquery.setProjection(Property.forName("id"));
		cri.add(Subqueries.exists(existMonumentosSuquery));;


		//Que su número sea mayor que X
		DetachedCriteria countMonumentosSuquery = DetachedCriteria.forClass(Monumento.class , "monumento");
		countMonumentosSuquery.add( Property.forName("monumento.ciudad.id").eqProperty("ciudadx.id"));
		countMonumentosSuquery.setProjection(Projections.count("id"));
		cri.add(Subqueries.lt(new Long(numMonumentos), countMonumentosSuquery));
		*/


		cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cri.list();
	}

}
