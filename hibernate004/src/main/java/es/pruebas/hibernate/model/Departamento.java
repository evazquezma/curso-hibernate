package es.pruebas.hibernate.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEPARTAMENTO")
public class Departamento {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nombre;


	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="nombre", column= @Column(name="NOMBRE_RESPONSABLE")),
	})
	private Responsable responsable;

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

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
}
