package es.pruebas.hibernate.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Hibernate015 {


	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application-context.xml");
context.containsBean("xxx");
	}
}
