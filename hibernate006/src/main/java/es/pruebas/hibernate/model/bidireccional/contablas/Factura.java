package es.pruebas.hibernate.model.bidireccional.contablas;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BD_CT_FACTURA")
public class Factura {

	@Id @GeneratedValue
	private Long id;

	/**
	 * Una factura tiene una operación, y una operación sólo está en una factura
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="UD_CT_FACT_OPERACION",
		joinColumns = @JoinColumn(name="idFactura"),
		inverseJoinColumns = @JoinColumn(name="idOperacion"))
	private OperacionContable operacion;


	/**
	 * Una factura tiene un único cliente
	 */
	@ManyToOne
	@JoinTable(name="UD_CT_FACT_CLIENTE",
		joinColumns = @JoinColumn(name="idFactura"),
		inverseJoinColumns = @JoinColumn(name="idCliente"))
	private Cliente cliente;


	/**
	 * Una factura tiene varias líneas
	 */
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="UD_CT_FACT_LINEA",
		joinColumns = @JoinColumn(name="idFactura"),
		inverseJoinColumns = @JoinColumn(name="idLinea"))
	private Set<LineaFactura> lineas;


	/**
	 * Una factura puede tener varios responsables, y un responsable puede estar asociado a varias facturas.
	 * Por defecto, hibernate ya crea una tabla intermedia. Con @JoinTable se pueden afinar más parámetros.
	 */
	@ManyToMany
	@JoinTable(name="UD_CT_FACT_RESPONSABLE",
		joinColumns = @JoinColumn(name="idFactura"),
		inverseJoinColumns = @JoinColumn(name="idResponsable"))
	private Set<Responsable> responsables;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OperacionContable getOperacion() {
		return operacion;
	}

	public void setOperacion(OperacionContable operacion) {
		this.operacion = operacion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<LineaFactura> getLineas() {
		return lineas;
	}

	public void setLineas(Set<LineaFactura> lineas) {
		this.lineas = lineas;
	}

	public Set<Responsable> getResponsables() {
		return responsables;
	}

	public void setResponsables(Set<Responsable> responsables) {
		this.responsables = responsables;
	}
}
