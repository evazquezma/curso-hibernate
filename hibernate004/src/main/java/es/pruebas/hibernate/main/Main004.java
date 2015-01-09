package es.pruebas.hibernate.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.Departamento;
import es.pruebas.hibernate.model.Empresa;

public class Main004 {
	private final Logger logger = LoggerFactory.getLogger(getClass());



	public void guardar() {
		Session session1 = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction1 = session1.beginTransaction();

		try {
			Empresa empresa = new Empresa();
			empresa.getId().setCif("00000001R");
			empresa.getId().setOrdinal(1);

			empresa.setNombre("Empresa 1");

			Departamento departamento = new Departamento();
			departamento.setNombre("departamento 1");

			empresa.setDepartamentos(new HashSet<Departamento>());
			empresa.getDepartamentos().add(departamento);

			session1.save(empresa);
			transaction1.commit();
		}
		catch (Exception e) {
			logger.error("Error", e);
			transaction1.rollback();
			throw new RuntimeException(e);
		}
	}




	public void listar() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		List<Empresa> empresas = new ArrayList<Empresa>();
		try {
			session.getTransaction().begin();

			empresas = session.createCriteria(Empresa.class).list();

			session.getTransaction().commit();
		}
		catch (Exception e) {
			logger.error("Error", e);
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		}



		for (Empresa empresa : empresas) {
			System.out.println(empresa);
			if (empresa.getDepartamentos() != null) {
				for (Departamento departamento : empresa.getDepartamentos()) {
					System.out.println(departamento);
				}
			}
		}
	}



	public static void main(String[] args) {
		Main004 main = new Main004();
		try {
			main.guardar();
			main.listar();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}

}
