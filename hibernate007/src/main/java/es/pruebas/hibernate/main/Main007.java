package es.pruebas.hibernate.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.pruebas.hibernate.HibernateUtil;

public class Main007 {
	private final Logger logger = LoggerFactory.getLogger(getClass());


	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();

		try {
			Herencia herencia = new Herencia();
			herencia.joined();
			herencia.single();
			herencia.tablePerClass();
		}
		finally {
			HibernateUtil.getSessionFactory().close();
		}
	}

}
