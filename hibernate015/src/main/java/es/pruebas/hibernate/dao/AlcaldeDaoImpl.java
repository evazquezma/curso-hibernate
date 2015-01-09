package es.pruebas.hibernate.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.pruebas.hibernate.model.Alcalde;

@Repository("alcaldeDao")
public class AlcaldeDaoImpl implements AlcaldeDao {
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	@Autowired
	public AlcaldeDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Alcalde> getAlcaldesMonumentoMasAntiguo(Integer antiguedadMonumento) {
		Criteria cri = getSession().createCriteria(Alcalde.class);
		Criteria criMonumento = cri.createCriteria("ciudad").createCriteria("monumentos");

		Calendar cal = Calendar.getInstance();

		criMonumento.add(Restrictions.lt("anhoInauguracion", cal.get(Calendar.YEAR) - antiguedadMonumento));
		cri.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		return cri.list();
	}
}
