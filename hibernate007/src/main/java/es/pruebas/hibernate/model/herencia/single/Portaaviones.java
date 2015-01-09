package es.pruebas.hibernate.model.herencia.single;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Portaaviones")
public class Portaaviones extends Barco {
	private Integer numeroAviones;
	private Integer numeroHelicopteros;


	public Integer getNumeroAviones() {
		return numeroAviones;
	}

	public void setNumeroAviones(Integer numeroAviones) {
		this.numeroAviones = numeroAviones;
	}

	public Integer getNumeroHelicopteros() {
		return numeroHelicopteros;
	}

	public void setNumeroHelicopteros(Integer numeroHelicopteros) {
		this.numeroHelicopteros = numeroHelicopteros;
	}
}
