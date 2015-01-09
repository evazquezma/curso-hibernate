package es.pruebas.hibernate.model.bidireccional.sintablas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BD_ST_PRESIDENTE")
public class Presidente {
	@Id @GeneratedValue
	private Long id;

	@OneToOne
	private Pais pais;


}
