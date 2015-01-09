package es.pruebas.hibernate.main;



import org.hibernate.Session;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.Departamento;

public class Merge {


	/**
	 * Prueba para ver que salta una excepción cuando se intenta meter en el contexto de persistencia un objeto con un id,
	 * habiendo asociado a ese mismo contexto otro objeto con ese mismo id.
	 */
	public void errorConUpdatePorIdDuplicadoEnContexto() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("Hacer update sobre un objeto habiendo otro en el cotexto");
		System.out.println("---------------------------------------------------------------");

		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Departamento departamento = new Departamento();
			departamento.setNombre("departamento existente en base de datos");
			session.save(departamento);
			session.getTransaction().commit();
			long id = departamento.getId();
			System.out.println("Guardado departamento por primera vez y generado id: " + id);
			//Ahora la sesión está cerrada y el departamento está en estado detached.


			//Abro una nueva sesión y le pido otra vez ese mismo id, pero me va a traer un objeto nuevo
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Departamento departamentoRecuperado = (Departamento) session.load(Departamento.class, id);
			System.out.println("Recuperado departamento con id " + departamentoRecuperado.getId());

			//Al intentar guardar el original va a saltar una excepción, porque ya tengo otro objeto "distinto"
			//en el contexto de persistencia con ese mismo id.
			departamento.setNombre("nombre cambiado");
			session.saveOrUpdate(departamento); //session.update(departamento);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}


	/**
	 * Con merge, primero se mira si ya existe un objeto con ese id en el contexto de persistencia.
	 * Si está, primero se reemplaza actualizando sus valores.
	 * En todo caso, siempre se hace la actualización contra base de datos.
	 */
	public void solucionarIdDuplicadoEnContextoConMerge() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("Hacer update sobre un objeto habiendo otro en el cotexto");
		System.out.println("---------------------------------------------------------------");

		Session session = null;

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Departamento departamento = new Departamento();
			departamento.setNombre("departamento existente en base de datos");
			session.save(departamento);
			session.getTransaction().commit();
			long id = departamento.getId();
			System.out.println("Guardado departamento por primera vez y generado id: " + id);
			//Ahora la sesión está cerrada y el departamento está en estado detached.


			//Abro una nueva sesión y le pido otra vez ese mismo id, pero me va a traer un objeto nuevo
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			Departamento departamentoRecuperado = (Departamento) session.load(Departamento.class, id);
			System.out.println("Recuperado departamento con id " + departamentoRecuperado.getId());

			//Usando merge, primero se actualiza el objeto existente en el contexto de persistencia, y luego se hace el save.
			//De este modo se sigue cumpliendo la condición de tener un id único en todo momento.
			departamento.setNombre("nombre cambiado");
			session.merge(departamento); //<---------------------------

			System.out.println("Ahora los dos objetos son el mismo y ambos están actualizados:");
			System.out.println("Objeto recuperado de base de datos, pero no actualizado manualmente: " + departamentoRecuperado.getId() + " - " + departamentoRecuperado.getNombre());
			System.out.println("Objeto inicialmente detached y luego mergeado: " + departamentoRecuperado.getId() + " - " + departamentoRecuperado.getNombre());

			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}

	}





	/**
	 * Pruebas sobre el funcionamiento del update en distintas transacciones. Hibernate siempre lanza directamente el update contra base
	 * de datos porque tienen asignado un id.
	 */
	public void updateEnVariasTransacciones() {
		System.out.println("---------------------------------------------------------------");
		System.out.println("Pruebas con update");
		System.out.println("---------------------------------------------------------------");
		Session session = null;

		Departamento departamento = new Departamento();
		try {
			//Primer salvado dentro de una transacción
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento.setNombre("departamento 1 - primera descripción");
			session.save(departamento);
			session.getTransaction().commit();
			System.out.println("Guardado departamento por primera vez y generado id: " + departamento.getId());


			//Segundo salvado dentro de otra transacción
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento.setNombre("departamento 1 - segunda descripción");
			session.update(departamento);
			session.getTransaction().commit();
			System.out.println("Update del mismo departamenteo por segunda vez y generado id: " + departamento.getId());


			//Tercer salvado Se crea un ojbeto nuevo y se le asigna el mismo id
			Departamento departamento2 = new Departamento();
			departamento2.setId(departamento.getId());

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().begin();
			departamento2.setNombre("departamento 1 - nueva instancia");
			session.update(departamento);
			session.getTransaction().commit();
			System.out.println("Update de un objeto nuevo, pero asignando el mismo id y generado id: " + departamento2.getId());
		}
		catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}




	public static void main(String[] args) {
		Merge merge = new Merge();
		try {
			merge.updateEnVariasTransacciones();
			System.out.println("\n\n\n");

			merge.errorConUpdatePorIdDuplicadoEnContexto();
			System.out.println("\n\n\n");

			merge.solucionarIdDuplicadoEnContextoConMerge();
			System.out.println("\n\n\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}
}
