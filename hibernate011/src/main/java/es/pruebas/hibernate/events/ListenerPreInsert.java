package es.pruebas.hibernate.events;

import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ListenerPreInsert implements PreInsertEventListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -2409219714589581100L;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		logger.info("Se va guardar la entidad "+ event.getEntity());
		return false;
	}

}