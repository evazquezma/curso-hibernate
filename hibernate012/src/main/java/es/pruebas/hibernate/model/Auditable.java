package es.pruebas.hibernate.model;

import java.util.Date;

public interface Auditable {
	public void setUsuarioActReg(String usuario);
	
	public void setFechaActReg(Date fecha);
}
