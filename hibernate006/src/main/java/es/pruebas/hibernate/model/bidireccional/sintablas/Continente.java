package es.pruebas.hibernate.model.bidireccional.sintablas;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="BD_ST_CONTINENTE")
public class Continente {
	@Id @GeneratedValue
	private Long id;


	@OneToMany(mappedBy="continente")
	private Set<Pais> paises;


}
