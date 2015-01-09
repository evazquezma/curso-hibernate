package es.pruebas.hibernate.main;

import org.hibernate.Session;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.Empresa;

public class LoadvsGet {

	public void run() {
		// Abro una transacción para insertar el objeto
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Empresa empresa = new Empresa();
		empresa.setCif("00000002R");
		empresa.setNombre("Empresa 1");

		session.save(empresa);

		session.getTransaction().commit();

		// Abro una nueva transacción para recuperal por get
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Empresa empresaGet = (Empresa) session.get(Empresa.class, empresa.getId());
		session.getTransaction().commit();

		// Abro una nueva transacción para recuperal por load
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.getTransaction().begin();
		Empresa empresaLoad = (Empresa) session.load(Empresa.class,	empresa.getId());
		session.getTransaction().commit();

		// Una vez cerradas las sesiones, intento pintar los dos objetos
		System.out.println(empresaGet);
		System.out.println(empresaLoad);
	}



	public static void main(String[] args) {
		LoadvsGet loadvsGet = new LoadvsGet();
		try {
			loadvsGet.run();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}
}
