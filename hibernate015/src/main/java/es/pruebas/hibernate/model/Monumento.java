package es.pruebas.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MONUMENTO")
public class Monumento {
	@Id @GeneratedValue
	private Long Id;

	private String nombre;
	private Integer anhoInauguracion;
	private Integer numVisitantes;

	@ManyToOne
	@JoinColumn(name="idCiudad")
	private Ciudad ciudad;

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getAnhoInauguracion() {
		return anhoInauguracion;
	}
	public void setAnhoInauguracion(Integer anhoInauguracion) {
		this.anhoInauguracion = anhoInauguracion;
	}
	public Integer getNumVisitantes() {
		return numVisitantes;
	}
	public void setNumVisitantes(Integer numVisitantes) {
		this.numVisitantes = numVisitantes;
	}
}
