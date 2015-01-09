package es.pruebas.hibernate.model.herencia.single;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="BARCO")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipoBarco", discriminatorType=DiscriminatorType.STRING)
public class Barco {

	@Id @GeneratedValue
	private Long id;

	private String nombre;
	private Float manga;
	private Float eslora;


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
	public Float getManga() {
		return manga;
	}
	public void setManga(Float manga) {
		this.manga = manga;
	}
	public Float getEslora() {
		return eslora;
	}
	public void setEslora(Float eslora) {
		this.eslora = eslora;
	}
}
