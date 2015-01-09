package es.pruebas.hibernate.model.herencia.tableperclass;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="AVION")
public class Avion extends Vehiculo {

	private Integer horasDeVuelo;
	private Float maximaAltitud;


	public Integer getHorasDeVuelo() {
		return horasDeVuelo;
	}
	public void setHorasDeVuelo(Integer horasDeVuelo) {
		this.horasDeVuelo = horasDeVuelo;
	}
	public Float getMaximaAltitud() {
		return maximaAltitud;
	}
	public void setMaximaAltitud(Float maximaAltitud) {
		this.maximaAltitud = maximaAltitud;
	}
}
