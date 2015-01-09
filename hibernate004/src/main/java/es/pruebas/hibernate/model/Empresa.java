package es.pruebas.hibernate.model;

import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EMPRESA")
public class Empresa {
	@EmbeddedId
	@AttributeOverrides({
	    @AttributeOverride(name="cif", column= @Column(name="CIF_RENOMBRADO")),
	    @AttributeOverride(name="ordinal", column= @Column(name="ordinal"))
	})
	private EmpresaPK id = new EmpresaPK();

	private String nombre;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumns ({
	    @JoinColumn(name="empresa_cif", referencedColumnName = "CIF_RENOMBRADO"),
	    @JoinColumn(name="empresa_ordinal", referencedColumnName = "ordinal"),
	})
	private Set<Departamento> departamentos;


	@Embedded
	@AttributeOverrides({
	    @AttributeOverride(name="nombre", column= @Column(name="NOMBRE_RESPONSABLE")),
	})
	private Responsable responsable;



	public EmpresaPK getId() {
		return id;
	}

	public void setId(EmpresaPK id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(Set<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}
}
