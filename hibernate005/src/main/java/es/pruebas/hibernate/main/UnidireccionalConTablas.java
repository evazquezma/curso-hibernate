package es.pruebas.hibernate.main;



import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.pruebas.hibernate.HibernateUtil;
import es.pruebas.hibernate.model.unidireccional.contablas.Cliente;
import es.pruebas.hibernate.model.unidireccional.contablas.Factura;
import es.pruebas.hibernate.model.unidireccional.contablas.LineaFactura;
import es.pruebas.hibernate.model.unidireccional.contablas.OperacionContable;
import es.pruebas.hibernate.model.unidireccional.contablas.Responsable;

public class UnidireccionalConTablas {

	public void run() {
		System.out.println("----------------------------------------------");
		System.out.println("------Probando UnidireccionalConTablas  ------");
		System.out.println("----------------------------------------------");

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();

		Factura factura = new Factura();
		factura.setOperacion(obtenerOperacion());
		factura.setCliente(obtenerCliente(session));
		factura.setLineas(obtenerLineas());
		factura.setResponsables(obtenerResponsables(session));
		session.save(factura);

		tx.commit();


		session = HibernateUtil.getSessionFactory().getCurrentSession();
		tx = session.beginTransaction();
		Factura facturaDB = (Factura) session.get(Factura.class, factura.getId());

		//Se pinta antes de cerrar la transacción para hacedr cargas vagas
		pintarFactura(facturaDB);

		tx.commit();
	}



	private void pintarFactura(Factura factura) {
		System.out.println("---------------------------------");
		System.out.println("--------- Pintando Factura ------");
		System.out.println("---------------------------------");

		System.out.println(factura);

		for (LineaFactura linea : factura.getLineas()) {
			System.out.println(linea);
		}

		for (Responsable responsable : factura.getResponsables()) {
			System.out.println(responsable);
		}
	}



	/**
	 * Se asume que la operación contable se crea junto con la factura
	 * @return
	 */
	private OperacionContable obtenerOperacion() {
		return new OperacionContable();
	}

	/**
	 * El cliente tiene entidad por sí mismo, con lo que a la hora de asignárselo a una factura
	 * debe existir previamente en base de datos
	 */
	private Cliente obtenerCliente(Session session) {
		Cliente cliente = new Cliente();
		session.save(cliente);
		return cliente;
	}

	/**
	 * Las líneas se crean por cascade
	 * @return
	 */
	private Set<LineaFactura> obtenerLineas() {
		Set<LineaFactura> lineas = new HashSet<LineaFactura>();
		lineas.add(new LineaFactura());
		lineas.add(new LineaFactura());
		lineas.add(new LineaFactura());
		return lineas;
	}


	/**
	 * Los responsables deben existir previamente
	 * @param session
	 * @return
	 */
	private Set<Responsable> obtenerResponsables(Session session) {
		Set<Responsable> responsables = new HashSet<Responsable>();

		Responsable responsable1 = new Responsable();
		session.save(responsable1);

		Responsable responsable2 = new Responsable();
		session.save(responsable2);

		responsables.add(responsable1);
		responsables.add(responsable2);
		return responsables;
	}

}
