package es.pruebas.hibernate.model.bidireccional.contablas;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="BD_CT_CLIENTE")
public class Cliente {

	@Id @GeneratedValue
	private Long id;

	@OneToMany
	private Set<Factura> facturas;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(Set<Factura> facturas) {
		this.facturas = facturas;
	}

}
