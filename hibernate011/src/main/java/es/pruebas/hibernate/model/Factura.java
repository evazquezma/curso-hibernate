package es.pruebas.hibernate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="FACTURA")
public class Factura implements Auditable {

	@Id @GeneratedValue
	private Long id;

	private String numero;

	@Enumerated(EnumType.STRING)
	private TipoFactura tipo;

	private Float valor;


	private String usuarioActReg;

	private Date fechaActReg;


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



	public String getUsuarioActReg() {
		return usuarioActReg;
	}

	@Override
	public void setUsuarioActReg(String usuarioActReg) {
		this.usuarioActReg = usuarioActReg;
	}

	public Date getFechaActReg() {
		return fechaActReg;
	}

	@Override
	public void setFechaActReg(Date fechaActReg) {
		this.fechaActReg = fechaActReg;
	}



	@Override
	public String toString() {
		return "Factura [id=" + id + ", numero=" + numero + ", tipo=" + tipo
				+ ", valor=" + valor + "]";
	}

}
