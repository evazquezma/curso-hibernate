package es.pruebas.hibernate.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="EMPRESA")
public class Empresa {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length=10, name="nombre_cambiado")
	private String nombre;

	@Column(unique=true)
	private String cif;

	@Transient
	private String campoNoMapeado;



	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Departamento> departamentos;



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

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}





	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nombre=" + nombre + ", cif=" + cif
				+ ", campoNoMapeado=" + campoNoMapeado + "]";
	}

}
