package es.pruebas.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ALCALDE")
public class Alcalde {
	@Id @GeneratedValue
	private Long Id;

	private String nombre;
	private Integer numeroMandatos;
	private Integer juiciosPorCorrupcion;

	@OneToOne
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
	public Integer getNumeroMandatos() {
		return numeroMandatos;
	}
	public void setNumeroMandatos(Integer numeroMandatos) {
		this.numeroMandatos = numeroMandatos;
	}
	public Integer getJuiciosPorCorrupcion() {
		return juiciosPorCorrupcion;
	}
	public void setJuiciosPorCorrupcion(Integer juiciosPorCorrupcion) {
		this.juiciosPorCorrupcion = juiciosPorCorrupcion;
	}


	@Override
	public String toString() {
		return "Alcalde [Id=" + Id + ", nombre=" + nombre + "]";
	}

}
