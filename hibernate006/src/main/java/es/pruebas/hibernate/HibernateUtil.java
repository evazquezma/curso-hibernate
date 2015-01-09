package es.pruebas.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.SessionFactoryObserver;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
        try {
        	Configuration configuration = new Configuration();
        	configuration.configure("hibernate.cfg.xml");

        	configuration.setSessionFactoryObserver(
        	        new SessionFactoryObserver() {
        	            /**
						 *
						 */
						private static final long serialVersionUID = -5625182154582863785L;

						@Override
        	            public void sessionFactoryCreated(SessionFactory factory) {}
        	            @Override
        	            public void sessionFactoryClosed(SessionFactory factory) {
        	            	System.out.println("Cerrando registro");
        	                ((StandardServiceRegistryImpl) serviceRegistry).destroy();
        	            }
        	        }
        	);


        	serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        	return sessionFactory;
        }
        catch (Throwable ex) {
          ex.printStackTrace();
          throw new ExceptionInInitializerError(ex);
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static ServiceRegistry getServiceRegistry() {
    	return serviceRegistry;
    }
}