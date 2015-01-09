package es.pruebas.hibernate.main;

import org.hibernate.Session;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.Departamento;

public class Save {


	/**
	 * Al guardar dos veces una misma entidad, pero dentro de la misma transacción. Sólo se crea un único id, además,
	 * si el objeto no se modifica entre los dos salvados, la segunda vez no se va contra base de datos.
	 */
	public void saveEnMismaTransaccion() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("Hacer save sobre el mismo objeto en la misma transacciones");
		System.out.println("---------------------------------------------------------------");
		Session session = null;

		Departamento departamento = new Departamento();
		try {
			//Primer salvado dentro de una transacción
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento.setNombre("departamento 1");
			session.save(departamento);
			session.flush();
			System.out.println("Guardado departamento por primera vez y generado id: " + departamento.getId());

			departamento.setNombre("departamento 1 - modificado");
			session.save(departamento);
			session.getTransaction().commit();
			System.out.println("Guardado el mismo departamenteo por segunda vez y generado id: " + departamento.getId());
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}



	/**
	 * Al guardar dos veces una misma entidad en dos transacciones distintas, realmente se crean dos registros en base de datos
	 */
	public void saveEnDosTransacciones() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("Hacer save sobre el mismo objeto en dos transacciones distintas");
		System.out.println("---------------------------------------------------------------");
		Session session = null;

		Departamento departamento = new Departamento();
		try {
			//Primer salvado dentro de una transacción
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento.setNombre("departamento 2");
			session.save(departamento);
			session.getTransaction().commit();
			System.out.println("Guardado departamento por primera vez y generado id: " + departamento.getId());

			//Segundo salvado dentro de otra transacción
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento.setNombre("departamento 2 - modificado");
			session.save(departamento);
			session.getTransaction().commit();
			System.out.println("Guardado el mismo departamenteo por segunda vez y generado id: " + departamento.getId());
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}




	/**
	 * Al hacer el save or update la segunda vez, se detecta que el objeto ya tiene un id asigando y hace el update, en vez de
	 * crear un registro nuevo con otro id.
	 */
	public void saveOrUpdateEnDistintasTransacciones() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("Hacer saveOrUpdate sobre el mismo objeto en la transacciones distintas");
		System.out.println("---------------------------------------------------------------");
		Session session = null;

		Departamento departamento = new Departamento();
		try {
			//Primer salvado dentro de una transacción
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento.setNombre("departamento 3");
			session.save(departamento);
			session.getTransaction().commit();
			System.out.println("Guardado departamento por primera vez y generado id: " + departamento.getId());

			//Segundo salvado dentro de otra transacción
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento.setNombre("departamento 3 - modificado");
			session.saveOrUpdate(departamento);
			session.getTransaction().commit();
			System.out.println("Guardado el mismo departamenteo por segunda vez, con save or update, y generado id: " + departamento.getId());
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}




	public static void main(String[] args) {
		Save save = new Save();
		try {
			save.saveEnDosTransacciones();
			save.saveEnMismaTransaccion();
			save.saveOrUpdateEnDistintasTransacciones();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}
}
