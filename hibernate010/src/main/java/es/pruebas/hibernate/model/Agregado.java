package es.pruebas.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AGREGADO")
public class Agregado {

	@Id
	private TipoFactura tipo;

	private Integer numeroFacturas;

	private Float total;


	public TipoFactura getTipo() {
		return tipo;
	}

	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}


	public Integer getNumeroFacturas() {
		return numeroFacturas;
	}

	public void setNumeroFacturas(Integer numeroFacturas) {
		this.numeroFacturas = numeroFacturas;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}




	@Override
	public String toString() {
		return "Agregado [tipo=" + tipo + ", numeroFacturas=" + numeroFacturas
				+ ", total=" + total + "]";
	}
}
