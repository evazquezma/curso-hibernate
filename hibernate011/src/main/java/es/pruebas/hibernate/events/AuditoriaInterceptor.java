package es.pruebas.hibernate.events;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.pruebas.hibernate.model.Auditable;

public class AuditoriaInterceptor extends EmptyInterceptor {
	/**
	 *
	 */
	private static final long serialVersionUID = -5032329715418881012L;

	private final Logger logger = LoggerFactory.getLogger(getClass());



	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("on save de la entidad " + entity);

		if (entity instanceof Auditable) {
			logger.info("Es auditable");

			 for ( int i=0; i < propertyNames.length; i++ ) {
	                if ("usuarioActReg".equals(propertyNames[i]) ) {
	                	state[i] = getUsuarioAutenticado();
	                }
	                else  if ("fechaActReg".equals(propertyNames[i]) ) {
	                	state[i] = new Date();
	                }
	            }

			 return true;
		}

		return false;
	}





	private Object getUsuarioAutenticado() {
		return "Usuario del SecurityContextHolder";
	}

}
