package es.pruebas.hibernate.main;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.dao.PersonaDao;
import es.pruebas.hibernate.dao.PersonaFiltro;
import es.pruebas.hibernate.dao.PersonaSQLDao;
import es.pruebas.hibernate.model.Direccion;
import es.pruebas.hibernate.model.Persona;

public class Main008 {
	private final Logger logger = LoggerFactory.getLogger(getClass());


	public void init() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		List<Persona> personas =  session.createCriteria(Persona.class).list();
		for(Persona persona : personas){
			session.delete(persona);
		}
		tx.commit();


		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();


		Calendar cal = Calendar.getInstance();

		Persona abuelo = new Persona();
		abuelo.setNombre("Abuelo");
		cal.set(1930, 0, 1);
		abuelo.setFechaNacimiento(cal.getTime());
		abuelo.setPadre(null);
		abuelo.setDirecciones(crearDireccionesAbuelo(abuelo));
		session.save(abuelo);


		Persona hijo = new Persona();
		hijo.setNombre("Hijo");
		cal.set(1960, 0, 1);
		hijo.setFechaNacimiento(cal.getTime());
		hijo.setPadre(abuelo);
		hijo.setDirecciones(crearDireccionesHijo(hijo));
		session.save(hijo);


		Persona nieto = new Persona();
		nieto.setNombre("Nieto");
		cal.set(1990, 0, 1);
		nieto.setFechaNacimiento(cal.getTime());
		nieto.setPadre(hijo);
		nieto.setDirecciones(crearDireccionesNieto(nieto));
		session.save(hijo);

		tx.commit();
	}

	private Set<Direccion> crearDireccionesAbuelo(Persona abuelo) {
		Set<Direccion> direcciones = new HashSet<Direccion>();

		Calendar cal = Calendar.getInstance();

		Direccion direccion = new Direccion();
		direccion.setDireccion("Calle abuelo 1");
		cal.set(1930, 0, 1);
		direccion.setFecha(cal.getTime());
		direccion.setPersona(abuelo);
		direcciones.add(direccion);


		direccion = new Direccion();
		direccion.setDireccion("Calle abuelo 2");
		cal.set(1950, 0, 1);
		direccion.setFecha(cal.getTime());
		direccion.setPersona(abuelo);
		direcciones.add(direccion);

		return direcciones;
	}

	private Set<Direccion> crearDireccionesHijo(Persona hijo) {
		Set<Direccion> direcciones = new HashSet<Direccion>();

		Calendar cal = Calendar.getInstance();

		Direccion direccion = new Direccion();
		direccion.setDireccion("Calle hijo 1");
		cal.set(1960, 0, 1);
		direccion.setFecha(cal.getTime());
		direccion.setPersona(hijo);
		direcciones.add(direccion);


		direccion = new Direccion();
		direccion.setDireccion("Calle hijo 2");
		cal.set(1970, 0, 1);
		direccion.setFecha(cal.getTime());
		direccion.setPersona(hijo);
		direcciones.add(direccion);


		direccion = new Direccion();
		direccion.setDireccion("Calle hijo 3");
		cal.set(1980, 0, 1);
		direccion.setFecha(cal.getTime());
		direccion.setPersona(hijo);
		direcciones.add(direccion);

		return direcciones;
	}

	private Set<Direccion> crearDireccionesNieto(Persona nieto) {
		Set<Direccion> direcciones = new HashSet<Direccion>();

		Calendar cal = Calendar.getInstance();

		Direccion direccion = new Direccion();
		direccion.setDireccion("Calle nieto 1");
		cal.set(1990, 0, 1);
		direccion.setFecha(cal.getTime());
		direccion.setPersona(nieto);
		direcciones.add(direccion);

		return direcciones;
	}




	public static void main(String[] args) {
		//PersonaDao personaDao = new PersonaCriteriaDao();
		//PersonaDao personaDao = new PersonaHQLDao();
		PersonaDao personaDao = new PersonaSQLDao();

		Main008 main = new Main008();
		try {
			main.init();


			PersonaFiltro filtro = new PersonaFiltro();
			filtro.setNombre("hijo");
			filtro.setNombrePadre("abuelo");
			filtro.setDireccion("calle");
			for (Persona persona : personaDao.buscarPersonas(filtro)) {
				System.out.println(persona);
			}

			System.out.println(personaDao.getUltimaDireccionPersona(1l));


			System.out.println("Sin padres");
			for (Persona persona : personaDao.buscarPersonasSinPadres()) {
				System.out.println(persona);
			}

			System.out.println("Sin hijos");
			for (Persona persona : personaDao.buscarPersonasSinHijos()) {
				System.out.println(persona);
			}

		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}

}
