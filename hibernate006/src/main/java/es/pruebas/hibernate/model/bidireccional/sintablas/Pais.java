package es.pruebas.hibernate.model.bidireccional.sintablas;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * Clase ra�z para las pruebas
 * No puede haber una many-to-many porque eso implica tener una tabla intermedia.
 *
 * @author evazquezma
 *
 */
@Entity
@Table(name="BD_ST_PAIS")
public class Pais {
	@Id @GeneratedValue
	private Long id;


	/**
	 * Un pa�s tiene un presidente, y un presidente s�lo pertenece a un pa�s
	 */
	@OneToOne(fetch=FetchType.LAZY, optional=false, mappedBy="pais")
	private Presidente presidente;

	/**
	 * Un pa�s est� en un continente, y en un continente puede haber varios paises
	 */
	@ManyToOne
	private Continente continente;

	/**
	 * Un pa�s tiene varias provincias
	 */
	@OneToMany(mappedBy="pais")
	private Set<Provincia> provincias;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Presidente getPresidente() {
		return presidente;
	}

	public void setPresidente(Presidente presidente) {
		this.presidente = presidente;
	}

	public Continente getContinente() {
		return continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}

	public Set<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(Set<Provincia> provincias) {
		this.provincias = provincias;
	}

}
