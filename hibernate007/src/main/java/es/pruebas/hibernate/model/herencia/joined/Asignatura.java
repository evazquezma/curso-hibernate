package es.pruebas.hibernate.model.herencia.joined;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


/**
 * Herencia de tipo 3, joined
 * @author evazquezma
 *
 */
@Entity
@Table(name="ASIGNATURA")
@Inheritance(strategy=InheritanceType.JOINED)
public class Asignatura {

	@Id @GeneratedValue
	private Long id;

	private String nombre;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
