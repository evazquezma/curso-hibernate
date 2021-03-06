package es.pruebas.hibernate.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.pruebas.hibernate.model.Agregado;
import es.pruebas.hibernate.model.Factura;
import es.pruebas.hibernate.model.TipoFactura;
import es.pruebas.hibernate.service.AgregadoService;
import es.pruebas.hibernate.service.FacturaService;

public class Main009 {



	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context.xml");

		FacturaService facturaService = (FacturaService) context.getBean("facturaService");
		AgregadoService agregadoService = (AgregadoService) context.getBean("agregadoService");

		try {
			Agregado agregado = new Agregado();
			agregado.setTipo(TipoFactura.NORMAL);
			agregado.setTotal(100f);
			agregado.setNumeroFacturas(5);
			agregadoService.save(agregado);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		try {
			Factura factura = new Factura();
			factura.setTipo(TipoFactura.NORMAL);
			factura.setNumero("2014-00001");
			factura.setValor(90f);
			facturaService.guardarFactura(factura);
		}
		catch(Exception e) {
			e.printStackTrace();
		}


		System.out.println("-----------------------------");
		System.out.println("------ Resumen --------------");
		System.out.println("-----------------------------");
		for (Factura fact : facturaService.getAll()) {
			System.out.println(fact);
		}

		System.out.println();

		for (Agregado agrdo : agregadoService.getAll()) {
			System.out.println(agrdo);
		}

	}

}
