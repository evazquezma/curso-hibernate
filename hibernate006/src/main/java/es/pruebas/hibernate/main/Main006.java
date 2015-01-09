package es.pruebas.hibernate.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.pruebas.hibernate.HibernateUtil;

public class Main006 {
	private final Logger logger = LoggerFactory.getLogger(getClass());


	public static void main(String[] args) {
		try {

			BidireccionalSinTablas bidireccionalSinTablas = new BidireccionalSinTablas();
			bidireccionalSinTablas.run();

			BidireccionalConTablas bidireccionalConTablas = new BidireccionalConTablas();
			bidireccionalConTablas.run();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}

}
