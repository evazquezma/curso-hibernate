package es.pruebas.hibernate.main;



import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.bidireccional.sintablas.Continente;
import es.pruebas.hibernate.model.bidireccional.sintablas.Pais;
import es.pruebas.hibernate.model.bidireccional.sintablas.Presidente;
import es.pruebas.hibernate.model.bidireccional.sintablas.Provincia;

public class BidireccionalSinTablas {

	public void run() {
		System.out.println("----------------------------------------------");
		System.out.println("------Probando UnidireccionalSinTablas  ------");
		System.out.println("----------------------------------------------");

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		Pais pais = new Pais();
		pais.setContinente(obtenerContiente(session));
		pais.setPresidente(obtenerPresidente(session));
		pais.setProvincias(obtenerProvincias(session));

		session.save(pais);

		tx.commit();


		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		Pais paisDB = (Pais) session.get(Pais.class, pais.getId());

		//Se pinta antes de cerrar la transacción para hacedr cargas vagas
		pintarPais(paisDB);

		tx.commit();
	}


	private void pintarPais(Pais pais) {
		System.out.println("---------------------------------");
		System.out.println("--------- Pintando Pais    ------");
		System.out.println("---------------------------------");

		System.out.println(pais);

		for (Provincia provincia : pais.getProvincias()) {
			System.out.println(provincia);
		}
	}



	/**
	 * Debe existir, así que se crea uno
	 * @param session
	 * @return
	 */
	private Continente obtenerContiente(Session session) {
		Continente continente = new Continente();
		session.save(continente);
		return continente;
	}

	/**
	 * Debe existir, así que se crea uno
	 * @param session
	 * @return
	 */
	private Set<Provincia> obtenerProvincias(Session session) {
		Set<Provincia> provincias = new HashSet<Provincia>();

		Provincia provincia1 = new Provincia();
		session.save(provincia1);

		Provincia provincia2 = new Provincia();
		session.save(provincia2);

		provincias.add(provincia1);
		provincias.add(provincia2);
		return provincias;
	}

	/**
	 * Debe existir, así que se crea uno
	 * @param session
	 * @return
	 */
	private Presidente obtenerPresidente(Session session) {
		Presidente presidente = new Presidente();
		session.save(presidente);
		return presidente;
	}
}
