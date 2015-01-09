package es.pruebas.hibernate.model.herencia.tableperclass;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="COCHE")
public class Coche extends Vehiculo {

	private Integer kilometrosRecorridos;

	public Integer getKilometrosRecorridos() {
		return kilometrosRecorridos;
	}

	public void setKilometrosRecorridos(Integer kilometrosRecorridos) {
		this.kilometrosRecorridos = kilometrosRecorridos;
	}

}
