package es.pruebas.hibernate.model.bidireccional.contablas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BD_CT_LINEA_FACTURA")
public class LineaFactura {

	@Id @GeneratedValue
	private Long id;

	@ManyToOne
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
