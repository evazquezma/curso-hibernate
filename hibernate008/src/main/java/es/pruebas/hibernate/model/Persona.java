package es.pruebas.hibernate.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PERSONA")
public class Persona {

	@Id @GeneratedValue
	private Long id;

	private String nombre;

	private Date fechaNacimiento;

	@ManyToOne
	@JoinColumn(name="idPadre")
	private Persona padre;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="persona")
	private Set<Direccion> direcciones;

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

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Persona getPadre() {
		return padre;
	}

	public void setPadre(Persona padre) {
		this.padre = padre;
	}

	public Set<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(Set<Direccion> direcciones) {
		this.direcciones = direcciones;
	}


	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre
				+ ", fechaNacimiento=" + fechaNacimiento + ", padre=" + padre
				+ "]";
	}
}
