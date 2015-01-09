package es.pruebas.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.herencia.joined.AsignaturaOnLine;
import es.pruebas.hibernate.model.herencia.joined.AsignaturaPresencial;
import es.pruebas.hibernate.model.herencia.single.Crucero;
import es.pruebas.hibernate.model.herencia.single.Portaaviones;
import es.pruebas.hibernate.model.herencia.tableperclass.Avion;
import es.pruebas.hibernate.model.herencia.tableperclass.Coche;

public class Herencia {

	public void joined() {
		System.out.println("--------------------------------");
		System.out.println("----------- JOINED  ------------");
		System.out.println("--------------------------------");

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		AsignaturaOnLine asignaturaOnline = new AsignaturaOnLine();
		asignaturaOnline.setNombre("Fisica");
		asignaturaOnline.setUrl("http://fisica.com");
		session.save(asignaturaOnline);

		AsignaturaPresencial asignaturaPresencial = new AsignaturaPresencial();
		asignaturaPresencial.setNombre("Fisica");
		asignaturaPresencial.setAula("B1");
		session.save(asignaturaPresencial);

		tx.commit();


		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		System.out.println(session.get(AsignaturaOnLine.class, 1l));
		tx.commit();
	}

	public void single() {
		System.out.println("--------------------------------");
		System.out.println("----------- SINGLE  ------------");
		System.out.println("--------------------------------");

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Crucero crucero = new Crucero();
		crucero.setNombre("Queen Mary");
		crucero.setManga(50f);
		crucero.setEslora(200f);
		crucero.setNumeroPasajeros(2000);
		session.save(crucero);

		Portaaviones portaaviones = new Portaaviones();
		portaaviones.setNombre("Enterprise");
		portaaviones.setManga(60f);
		portaaviones.setEslora(300f);
		portaaviones.setNumeroAviones(80);
		portaaviones.setNumeroHelicopteros(10);
		session.save(portaaviones);

		tx.commit();



		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		System.out.println(session.get(Crucero.class, 1l));
		tx.commit();
	}


	public void tablePerClass() {
		System.out.println("--------------------------------");
		System.out.println("-------- TABLE PER CLASS -------");
		System.out.println("--------------------------------");

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		Coche coche = new Coche();
		coche.setMarca("Ford");
		coche.setMatricula("0000-AAA");
		coche.setKilometrosRecorridos(50000);
		session.save(coche);

		Avion avion = new Avion();
		avion.setMarca("Air Bus");
		avion.setMatricula("0000-B");
		avion.setMaximaAltitud(11000f);
		session.save(avion);

		tx.commit();



		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		System.out.println(session.get(Coche.class, 1l));
		tx.commit();
	}
}

