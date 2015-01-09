package es.pruebas.hibernate.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="FACTURA")
public class Factura {

	@Id @GeneratedValue
	private Long id;

	private String numero;

	@Enumerated(EnumType.STRING)
	private TipoFactura tipo;

	private Float valor;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoFactura getTipo() {
		return tipo;
	}

	public void setTipo(TipoFactura tipo) {
		this.tipo = tipo;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}


	@Override
	public String toString() {
		return "Factura [id=" + id + ", numero=" + numero + ", tipo=" + tipo
				+ ", valor=" + valor + "]";
	}

}
