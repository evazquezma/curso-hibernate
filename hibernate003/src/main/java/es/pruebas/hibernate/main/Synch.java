package es.pruebas.hibernate.main;

import org.hibernate.Session;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.Departamento;

public class Synch {



	public void testClearVsEvict() {
		// Abro una transacción para insertar el objeto
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Departamento departamento1 = new Departamento();
		departamento1.setNombre("departamento 1");
		session.save(departamento1);

		Departamento departamento2 = new Departamento();
		departamento2.setNombre("departamento 1");
		session.save(departamento2);

		session.getTransaction().commit();

		Long id1 = departamento1.getId();
		Long id2 = departamento2.getId();


		System.out.println("\n\n\n\n\n");
		// Abro una nueva transacción para recuperal por get
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();

		Departamento depRecuperado1 = (Departamento) session.get(Departamento.class, id1);
		Departamento depRecuperado2 = (Departamento) session.get(Departamento.class, id2);
		System.out.println("\nFin de la primera recuperación de objetos\n");

		// Borrando la sesión se ejecutarán dos nuevas querys
		session.clear();

		// Borrando el departamento 1, se ejecutará una nueva query
		//session.evict(depRecuperado1);

		// Si no se hace ni clear ni evict, no se ejecutarán más querys.

		session.get(Departamento.class, id1);
		session.get(Departamento.class, id2);

		session.getTransaction().commit();
	}



	public static void main(String[] args) {
		Synch synch = new Synch();
		try {
			synch.testClearVsEvict();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}
}
