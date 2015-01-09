package es.pruebas.hibernate.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.pruebas.hibernate.HibernateUtil;

public class Main005 {
	private final Logger logger = LoggerFactory.getLogger(getClass());


	public static void main(String[] args) {
		try {

			UnidireccionalSinTablas uniSinTablas = new UnidireccionalSinTablas();
			uniSinTablas.run();


			UnidireccionalConTablas uniConTablas = new UnidireccionalConTablas();
			uniConTablas.run();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}

}
