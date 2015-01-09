package es.pruebas.hibernate.model.herencia.joined;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="ID_ASIGNATURA")
public class AsignaturaPresencial extends Asignatura {
	private String aula;

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

}
