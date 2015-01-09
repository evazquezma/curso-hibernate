package es.pruebas.hibernate.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CIUDAD")
public class Ciudad {

	@Id @GeneratedValue
	private Long Id;

	private String nombre;

	private Integer poblacion;

	private Date fechaFundacion;

	@OneToOne(mappedBy="ciudad")
	private Alcalde alcalde;

	@OneToMany(mappedBy="ciudad")
	private List<Monumento> monumentos = new ArrayList<Monumento>();

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

	public Integer getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Integer poblacion) {
		this.poblacion = poblacion;
	}

	public Date getFechaFundacion() {
		return fechaFundacion;
	}

	public void setFechaFundacion(Date fechaFundacion) {
		this.fechaFundacion = fechaFundacion;
	}

	public Alcalde getAlcalde() {
		return alcalde;
	}

	public void setAlcalde(Alcalde alcalde) {
		this.alcalde = alcalde;
	}

	public List<Monumento> getMonumentos() {
		return monumentos;
	}

	public void setMonumentos(List<Monumento> monumentos) {
		this.monumentos = monumentos;
	}

	@Override
	public String toString() {
		return "Ciudad [Id=" + Id + ", nombre=" + nombre + "]";
	}



}
