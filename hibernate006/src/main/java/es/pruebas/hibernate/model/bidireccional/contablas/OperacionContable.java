package es.pruebas.hibernate.model.bidireccional.contablas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BD_CT_OPERACION_CONTABLE")
public class OperacionContable {

	@Id @GeneratedValue
	private Long id;

	@OneToOne
	private Factura factura;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
}
